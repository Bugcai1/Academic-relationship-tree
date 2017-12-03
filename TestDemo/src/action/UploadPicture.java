package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UploadPicture extends ActionSupport {

	private String ID;            //用户的ID
    private File uploadFile; // 上传的文件
    private String uploadFileContentType; // 文件类型
    private String uploadFileFileName; // 文件名字

    public String getID() {
    	return ID;
    }
    public void setID(String ID) {
    	this.ID=ID;
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
    	StringBuilder fileName=new StringBuilder(this.ID);
        for(int i=uploadFileContentType.length()-1;i>=0;i--) {
        	if(uploadFileContentType.charAt(i)=='/') {
        		fileName.append('.');
        		for(int j=i+1;j<uploadFileContentType.length();j++){
            		fileName.append(uploadFileContentType.charAt(j));
            	}
        		break;
        	}
        }
        this.uploadFileFileName=fileName.toString();
        System.out.println("------------------------------------------------");
        System.out.println(this.uploadFileFileName);
    }

    @Override
    public String execute() throws Exception {
   
        //判断UserPicture文件夹是否存在
    	 File dir =new File(ServletActionContext.getServletContext().getRealPath("/UserPicture"));    
    	//如果文件夹不存在则创建    
    	if  (!dir .exists()  && !dir .isDirectory()){       
    	    System.out.println("//不存在");  
    	    dir.mkdir();    
    	} else{  
    	    System.out.println("//目录存在");  
    	} 
    	
    	
        InputStream is = new FileInputStream(uploadFile); // 输入流
        String uploadPath = ServletActionContext.getServletContext().getRealPath("/UserPicture"); // 上传文件目录
        File file=new File(uploadPath+"/"+uploadFileFileName);
        System.out.println(uploadFileFileName);
        System.out.println(file.getPath());
        //文件存在就删除
        /*
        if(file.exists()){
        	if(file.delete()) {
        		System.out.println("删除文件成功");
        	};
        }
        */
        OutputStream os = new FileOutputStream(file); // 输出流
        
        byte[] buffer = new byte[1024];// 设置缓存
        int length = 0;
        // 读取uploadFile文件输出到toFile文件中
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        
        is.close(); // 关闭输入流
        os.close(); // 关闭输出流
        return SUCCESS;
    }
}
