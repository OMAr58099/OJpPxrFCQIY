// 代码生成时间: 2025-10-06 20:19:05
package com.example.blockchain;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;
import java.util.Map;

/**
 * BlockchainExplorer service class to interact with blockchain data.
 */
public class BlockchainExplorer {

    private final SqlSessionFactory sqlSessionFactory;

    /**
     * Constructor with SqlSessionFactory.
     * @param sqlSessionFactory The factory for creating SQL session.
     */
    public BlockchainExplorer(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Retrieves a list of blocks from the blockchain.
     * @return A list of block data.
     */
    public List<Map<String, Object>> getBlocks() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // Using a Mapper interface to interact with the database
            BlockMapper mapper = session.getMapper(BlockMapper.class);
            return mapper.selectAllBlocks();
        } catch (Exception e) {
            // Error handling
            throw new RuntimeException("Failed to get blocks from blockchain", e);
        }
    }

    /**
     * Retrieves a block by its hash.
     * @param blockHash The hash of the block to retrieve.
     * @return A map representing the block data.
     */
    public Map<String, Object> getBlockByHash(String blockHash) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            BlockMapper mapper = session.getMapper(BlockMapper.class);
            return mapper.selectBlockByHash(blockHash);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get block by hash: " + blockHash, e);
        }
    }
}

/*
 * BlockMapper.java
 *
 * This MyBatis mapper interface is used to map SQL queries to the BlockchainExplorer service.
 */
package com.example.blockchain;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

public interface BlockMapper {

    @Select("SELECT * FROM blocks")
    @Results(value = {
        @Result(property = "hash", column = "hash"),
        @Result(property = "previousHash", column = "previous_hash"),
        @Result(property = "timestamp", column = "timestamp"),
        @Result(property = "nonce", column = "nonce"),
        @Result(property = "transactions", column = "transactions")
    })
    List<Map<String, Object>> selectAllBlocks();

    @Select("SELECT * FROM blocks WHERE hash = #{hash}")
    Map<String, Object> selectBlockByHash(@Param("hash") String hash);
}
