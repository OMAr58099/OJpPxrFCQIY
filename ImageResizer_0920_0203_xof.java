// 代码生成时间: 2025-09-20 02:03:30
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import java.util.List;
import java.util.ArrayList;

// 图片尺寸批量调整器
public class ImageResizer {

    private static SqlSessionFactory sqlSessionFactory;
    private static final String MYBATIS_CONFIG = "mybatis-config.xml";
    private static final String IMAGE_DIRECTORY = "path/to/image/directory";
    private static final String RESIZED_IMAGE_DIRECTORY = "path/to/resized/image/directory";
    private static final int NEW_WIDTH = 800;
    private static final int NEW_HEIGHT = 600;
    private static final int SCALE_FACTOR = 1; // 保持比例缩放因子

    // 从MyBatis配置文件创建SqlSessionFactory
    static {
        try {
            String resource = MYBATIS_CONFIG;
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 调整图片尺寸
    public void resizeImages() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            try {
                // 获取所有图片路径
                List<String> imagePaths = getImagePaths(session);

                // 调整图片尺寸
                for (String imagePath : imagePaths) {
                    resizeImage(imagePath);
                }
            } catch (Exception e) {
                session.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
    }

    // 从数据库获取所有图片路径
    private List<String> getImagePaths(SqlSession session) throws Exception {
        ImageMapper mapper = session.getMapper(ImageMapper.class);
        return mapper.getAllImagePaths();
    }

    // 调整图片尺寸
    private void resizeImage(String imagePath) {
        try {
            File imageFile = new File(imagePath);
            BufferedImage originalImage = ImageIO.read(imageFile);
            double scaleX = (double) NEW_WIDTH / originalImage.getWidth();
            double scaleY = (double) NEW_HEIGHT / originalImage.getHeight();
            double scale = Math.min(scaleX, scaleY) * SCALE_FACTOR;

            int newWidth = (int) (originalImage.getWidth() * scale);
            int newHeight = (int) (originalImage.getHeight() * scale);

            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
            resizedImage.getGraphics().drawImage(
                originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH),
                0, 0, null
            );

            File resizedImageFile = new File(RESIZED_IMAGE_DIRECTORY + File.separator + imageFile.getName());
            ImageIO.write(resizedImage, "jpg", resizedImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MyBatis Mapper接口
    @Mapper
    public interface ImageMapper {
        @Select("SELECT image_path FROM images")
        List<String> getAllImagePaths();
    }

    // 主方法用于运行程序
    public static void main(String[] args) {
        ImageResizer imageResizer = new ImageResizer();
        imageResizer.resizeImages();
    }
}
