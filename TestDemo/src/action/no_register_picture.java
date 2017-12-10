package action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class no_register_picture extends ActionSupport {

	private int ID;   
    private File uploadFile;
    private String uploadFileContentType;
    private String uploadFileFileName; 

    public int getID() {
    	return ID;
    }
    public void setID(int ID) {
    	this.ID=ID;
    	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
    
    public File getUploadFile() {
        return uploadFile;
    }
    public void setUploadFile(File uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getUploadFileContentType() {
        return uploadFileContentType;
    }
    public void setUploadFileContentType(String uploadFileContentType) {
        this.uploadFileContentType = uploadFileContentType;
        System.out.println(this.uploadFileContentType);
    }

    public String getUploadFileFileName() {
        return uploadFileFileName;
    }
    public void setUploadFileFileName(String uploadFileFileName) {
    	StringBuilder fileName=new StringBuilder(this.ID+"");
    	
    	
        for(int i=uploadFileContentType.length()-1;i>=0;i--) {
        	if(uploadFileContentType.charAt(i)=='/') {
        		fileName.append('.');
        		for(int j=i+1;j<uploadFileContentType.length();j++){
            		fileName.append(uploadFileContentType.charAt(j));
            	}
        		break;
        	}
        }
        this.uploadFileFileName=ID+".jpeg";
        System.out.println("------------------------------------------------");
        System.out.println(this.uploadFileFileName);
    }

    public void write() throws Exception {
   
        
    	System.out.println("ID"+getUploadFile());
    	 File dir =new File(ServletActionContext.getServletContext().getRealPath("/UserPicture"));    
    	
    	if  (!dir .exists()  && !dir .isDirectory()){       
//    	    System.out.println("");  
    	    dir.mkdir();    
    	} else{  
    	    System.out.println("删除存在的图片");  
    	} 
    	
    	
        InputStream is = new FileInputStream(uploadFile);
        String uploadPath = ServletActionContext.getServletContext().getRealPath("/UserPicture"); 
        File file=new File(uploadPath+"/"+uploadFileFileName);
        System.out.println(uploadFileFileName);
        System.out.println(file.getPath());
        if(file.exists()){
        	if(file.delete()) {
        		System.out.println("delete");
        	};
        }
        OutputStream os = new FileOutputStream(file); 
        
        byte[] buffer = new byte[1024];
        int length = 0;
       
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        
        is.close();
        os.close();
        HttpServletResponse response=ServletActionContext.getResponse(); 
		response.setContentType("text/html;charset=utf-8");  
	    PrintWriter out = response.getWriter(); 
        out.println("great");
	    out.flush();  
	    out.close();  
    }
}

