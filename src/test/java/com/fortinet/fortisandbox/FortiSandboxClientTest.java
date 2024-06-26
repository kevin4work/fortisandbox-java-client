package com.fortinet.fortisandbox;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.fortinet.fortisandbox.model.LoginResponse;
import com.fortinet.fortisandbox.model.SubmitFileResponse;
import com.fortinet.fortisandbox.model.GetJobIdResponse;
import com.fortinet.fortisandbox.model.GetJobIdVerdictResponse;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.util.concurrent.TimeUnit;
/**
 * Unit test for simple App.
 */
public class FortiSandboxClientTest 
    extends TestCase
{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final static int SUCCESS = 0;
    private final static String FORTISANDBOX_IP = "165.84.173.22";
    private final static String FORTISANDBOX_API_USER = "admin";
    private final static String FORTISANDBOX_API_PASSWORD = "Fortinet100!";
    private final static String TEST_FILE_PATH = "src/test/resources/Gartner2021.pdf";
    private final static String TEST_FILE_NAME = "Gartner2021.pdf";
    
    
    FortiSandboxClient fortiSandboxclient;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FortiSandboxClientTest()
    {
        super();
        this.fortiSandboxclient = new FortiSandboxClient(FORTISANDBOX_IP);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FortiSandboxClientTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testLogin()
    {
        logger.debug(" ======== testLogin started ========");
        assertNotNull(getSession());
    }
    
    public void testLoginFailed()
    {
        logger.debug(" ======== testLoginFailed started ========");
        try{
            LoginResponse resp = fortiSandboxclient.login("admin", "sdfdfddfd!");
            assertNotNull(resp.getStatus());
            assertNotSame(SUCCESS, resp.getStatus().getCode());
            assertEquals("WRONG_CREDENTIAL", resp.getStatus().getMessage());
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void testSubmitFile()
    {
        logger.debug(" ======== testSubmitFile started ========");
        try {
            File file = new File(TEST_FILE_PATH);
            SubmitFileResponse submitResp = fortiSandboxclient.submitFile(file, TEST_FILE_NAME, getSession());
            assertNotNull(submitResp.getStatus());
            assertEquals(SUCCESS, submitResp.getStatus().getCode());
            assertNotNull(submitResp.getData().getSid());
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void testInvalidSession()
    {        
        logger.debug(" ======== testInvalidSession started ========");
        
        try {
            File file = new File(TEST_FILE_PATH);
            SubmitFileResponse resp = fortiSandboxclient.submitFile(file, TEST_FILE_NAME, "fakesessionsssss");
            assertNotNull(resp.getStatus());
            assertNotSame(SUCCESS, resp.getStatus().getCode());
            assertEquals("INVALID_SESSION", resp.getStatus().getMessage());
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void testGetJobId()
    {
        logger.debug(" ======== testGetJobId started ========");
        try {
            String sid = getSubmitID();
            //add time wait for job submission
            TimeUnit.SECONDS.sleep(10);
            
            GetJobIdResponse resp = fortiSandboxclient.getJobId(sid, getSession());
            assertNotNull(resp.getStatus());
            assertEquals(SUCCESS, resp.getStatus().getCode());
            assertNotNull(resp.getData().getJids());
            assertTrue(resp.getData().getTotal_jids()>0);
            
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void testGetJobIdPending()
    {
        logger.debug(" ======== testGetJobIdPending started ========");
        try {
            String sid = getSubmitID();
            
            // Immediately query for job id
            GetJobIdResponse resp = fortiSandboxclient.getJobId(sid, getSession());
            assertNotNull(resp.getStatus());
            assertEquals(SUCCESS, resp.getStatus().getCode());
            assertNotNull(resp.getData().getJids());
            assertTrue(resp.getData().getTotal_jids()==0);
            
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    public void testGetJobIdVerdict()
    {
        logger.debug(" ======== testGetJobIdVerdict started ========");
        try {
            String sid = getSubmitID();
            //add time wait for job submission
            TimeUnit.SECONDS.sleep(10);
            
            String jid = fortiSandboxclient.getJobId(sid, getSession()).getData().getJids()[0];
            GetJobIdVerdictResponse resp = fortiSandboxclient.getJobIdVerdict(jid, getSession());
            assertNotNull(resp.getStatus());
            assertEquals(SUCCESS, resp.getStatus().getCode());
            assertEquals(resp.getData().getFile_name(), TEST_FILE_NAME);
            assertEquals(resp.getData().getRating(), "Clean");
            
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private String getSession(){
        try {
            return fortiSandboxclient.login(FORTISANDBOX_API_USER, FORTISANDBOX_API_PASSWORD).getSession();
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    
    private String getSubmitID(){
        
        try {
            File file = new File(TEST_FILE_PATH);
            return fortiSandboxclient.submitFile(file, TEST_FILE_NAME, getSession()).getData().getSid();
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
