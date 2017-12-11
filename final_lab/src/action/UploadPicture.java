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

	private int ID;            //�û���ID
    private File uploadFile; // �ϴ����ļ�
    private String uploadFileContentType; // �ļ�����
    private String uploadFileFileName; // �ļ�����

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

    @Override
    public String execute() throws Exception {
   
        //�ж�UserPicture�ļ����Ƿ����
    	System.out.println("ID"+getUploadFile());
    	 File dir =new File(ServletActionContext.getServletContext().getRealPath("/UserPicture"));    
    	//����ļ��в������򴴽�    
    	if  (!dir .exists()  && !dir .isDirectory()){       
//    	    System.out.println("");  
    	    dir.mkdir();    
    	} else{  
    	    System.out.println("删除存在的图片");  
    	} 
    	
    	
        InputStream is = new FileInputStream(uploadFile); // ������
        String uploadPath = ServletActionContext.getServletContext().getRealPath("/UserPicture"); // �ϴ��ļ�Ŀ¼
        File file=new File(uploadPath+"/"+uploadFileFileName);
        System.out.println("�ļ���"+uploadFileFileName);
        System.out.println(file.getPath());
        //�ļ����ھ�ɾ��
        if(file.exists()){
        	if(file.delete()) {
        		System.out.println("ɾ���ļ��ɹ�");
        	};
        }
        OutputStream os = new FileOutputStream(file); // �����
        
        byte[] buffer = new byte[1024];// ���û���
        int length = 0;
        // ��ȡuploadFile�ļ������toFile�ļ���
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        
        is.close(); // �ر�������
        os.close(); // �ر������
        return "SUCCESS";
        
    }
}
