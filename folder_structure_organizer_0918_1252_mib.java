// 代码生成时间: 2025-09-18 12:52:43
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

// 文件夹结构整理器
public class FolderStructureOrganizer {

    // 配置MyBatis
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 读取MyBatis配置文件
            String resource = 