package com.yukharin.dao.userdao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.yukharin.dao.utils.JdbcUtils;
import com.yukharin.model.User;

class UserDaoTest {

    private static UserDao dao;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        dao = new UserDao();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        if (dao != null) {
            JdbcUtils.closeConnection(dao.getConnection());
        }
    }

    @Test
    void testAddAndGetUser() {
        try {
            User expected = new User(-15, "Killian", 67);
            dao.addUser(expected);
            User actual = dao.getUser(-15);
            assertEquals(expected, actual);
        } finally {
            if (dao != null) {
                dao.deleteUser(-15);
            }
        }
    }

    @Test
    void testDeleteUser() {
        try {
            User targetUser = new User(-2, "TestUser", 34);
            dao.addUser(targetUser);
            dao.deleteUser(-2);
            User expected = new User();
            User actual = dao.getUser(-2);
            assertEquals(expected, actual);
        } finally {
            if (dao != null) {
                dao.deleteUser(-2);
            }
        }
    }

    @Test
    void testUpdateUser() {
        try {
            User targetUser = new User(-3, "TestUser", 34);
            dao.addUser(targetUser);
            User expected = new User(-3, "ExpectedUser", 55);
            dao.updateUser(-3, expected);
            User actual = dao.getUser(-3);
            assertEquals(expected, actual);
        } finally {
            if (dao != null) {
                dao.deleteUser(-3);
            }
        }
    }

}
