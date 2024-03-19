package com.fortinet.fortisandbox;

import com.fortinet.fortisandbox.model.LoginResponse;
import com.fortinet.fortisandbox.model.SubmitFileResponse;
import com.fortinet.fortisandbox.model.GetJobIdResponse;
import com.fortinet.fortisandbox.model.GetJobIdVerdictResponse;

import java.io.File;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class FortiSandboxClient{
    private String ipAddress;
    private JsonRpcHttpClient jsonRpcHttpClient;
    private final static String URL_LOGIN = "/sys/login/user";
    private final static String URL_SUBMIT_FILE = "/alert/ondemand/submit-file";
    private final static String URL_GET_JOB_ID = "/scan/result/get-jobs-of-submission";
    private final static String URL_GET_JOB_ID_VERDICT = "/scan/result/job";
    private final static String URL_SUBMIT_FILE_TYPE = "file";
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public FortiSandboxClient(String ipAddress) {
        this.ipAddress = ipAddress;
        this.jsonRpcHttpClient = createJsonRpcClient();
    }

    public LoginResponse login(String username, String password) throws Throwable {
        LoginResponse response = null;
        Map<String, Object> params = new HashMap<>();
        params.put("url", URL_LOGIN);
        params.put("user", username);
        params.put("passwd", password);

        Object[] paramArray = new Object[1];
        paramArray[0] = params;
        response = jsonRpcHttpClient.invoke(null, JsonRpcHttpClient.EXEC, paramArray, LoginResponse.class);
        return response;
    }
    
    public SubmitFileResponse submitFile(File file, String filename, String session) throws Throwable {
        SubmitFileResponse response = null;
        byte[] fileContent = Files.readAllBytes(file.toPath());

        // Encode file content with Base64
        String encodedContent = Base64.getEncoder().encodeToString(fileContent);
        String encodedFilename = Base64.getEncoder().encodeToString(filename.getBytes());
        
        Map<String, Object> params = new HashMap<>();
        params.put("url", URL_SUBMIT_FILE);
        params.put("type", URL_SUBMIT_FILE_TYPE);
        params.put("file", encodedContent);
        params.put("filename", encodedFilename);
        
        Object[] paramArray = new Object[1];
        paramArray[0] = params;
        response = jsonRpcHttpClient.invoke(session, JsonRpcHttpClient.SET, paramArray, SubmitFileResponse.class);

        return response;
    }
    
    public GetJobIdResponse getJobId(String sid, String session) throws Throwable {
        GetJobIdResponse response = null;
        
        Map<String, Object> params = new HashMap<>();
        params.put("url", URL_GET_JOB_ID);
        params.put("sid", sid);
        
        Object[] paramArray = new Object[1];
        paramArray[0] = params;
        response = jsonRpcHttpClient.invoke(session, JsonRpcHttpClient.GET, paramArray, GetJobIdResponse.class);

        return response;
    }
    
    public GetJobIdVerdictResponse getJobIdVerdict(String jid, String session) throws Throwable {
        GetJobIdVerdictResponse response = null;
        
        Map<String, Object> params = new HashMap<>();
        params.put("url", URL_GET_JOB_ID_VERDICT);
        params.put("jid", jid);
        
        Object[] paramArray = new Object[1];
        paramArray[0] = params;
        response = jsonRpcHttpClient.invoke(session, JsonRpcHttpClient.GET, paramArray, GetJobIdVerdictResponse.class);

        return response;
    }
    
    private JsonRpcHttpClient createJsonRpcClient() {
        JsonRpcHttpClient client;
        try {
            URL url = new URL("https://" + ipAddress + "/jsonrpc");
            client = new JsonRpcHttpClient(url);
            SSLContext sslContext;
       
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[] { new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                }
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] certs,
                                String authType) throws CertificateException{
                }
                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] certs,
                                String authType) throws CertificateException{
                }
    
            }}, new SecureRandom());
            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };  
            client.setSslContext(sslContext);
            client.setHostNameVerifier(allHostsValid);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return client;
    }
    
    
    

}
