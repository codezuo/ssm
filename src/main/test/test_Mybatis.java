
import com.jgsu.dao.AccountDao;
import com.jgsu.domain.Account;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import com.jgsu.utils.SqlSessionFactoryUtils;


import java.util.List;
import java.util.logging.Logger;

public class test_Mybatis {
    /*测试查询数据
    * 不需要提交事务
    * */
    @Test
    public void test_01(){
        Logger logger = Logger.getLogger(String.valueOf(test_Mybatis.class));
        SqlSession sqlSession = null;
        try {
          //  sqlSession = SqlSessionFactoryUtils.openSqlSession();
            AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
            List<Account> list = accountDao.findAll();
            logger.info(list.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (sqlSession != null){
                sqlSession.close();
            }
        }
    }

 /*测试插入数据
 * 需要提交事务sqlSession.commit();
 * */
    @Test
    public void test_02(){
        Logger logger = Logger.getLogger(String.valueOf(test_Mybatis.class));
        SqlSession sqlSession = null;
        try {
         //   sqlSession = SqlSessionFactoryUtils.openSqlSession();
            AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
            Account account = new Account();
            account.setName("小小");
            account.setMoney( 4000D);
            accountDao.saveAccount(account);
            logger.info(account.toString());
            //提交事务：插入，删除，更新方法都需要提交事务，不然数据库内的内容不会改变
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (sqlSession != null){
                sqlSession.close();
            }
        }
    }
}
