package com.example.application.service.Impl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.application.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {
	
	/* アップロードの実行処理 */
	@Override
	public String uploadFile(MultipartFile multipartFile) {
		        
		// 格納するディレクトリのフルパスを取得
		String FullPath = "C:/pleiades/2022-06/workspace/SpringMySQLSample/src/main/resources/static/img/";
        
		// 引数に設定したディレクトリ内のファイルを取得する
        File fileNames = new File(FullPath);
        
        // ディレクトリ内のファイルをリスト型で取得する
        File fileList[] = fileNames.listFiles();
        
        // 新しいファイル名を定義しておく
        String NewfileName ="";
        
    	// アップロードされた画像の名前を取得
    	String fileName = multipartFile.getOriginalFilename();
    	
        // 拡張子を取得
        String extension = fileName.substring(fileName.lastIndexOf("."));
        
        // ディレクトリ内にファイルが存在すれば
        if(fileList != null) {
        	
            // ファイル名はディレクトリ内のファイル数+1に変更する
            // 拡張子の変更はしない
        	NewfileName = (fileList.length + 1) + extension;
        
        // ディレクトリ内にファイルが存在しなければファイル名は1.拡張子
        } else {
        	NewfileName = 1+ extension;
        }

        //格納先のフルパス ※事前に格納先フォルダ「UploadTest」をCドライブ直下に作成しておく
        Path filePath = Paths.get(FullPath + NewfileName);
        
        // データベースに格納するパスはstaticからの相対パス
        String DbPath = ("/img/" + NewfileName);
        
        
		try {
            //アップロードファイルをバイト値に変換
			byte[] bytes = multipartFile.getBytes();

            //バイト値を書き込む為のファイルを作成して指定したパスに格納
            OutputStream stream = Files.newOutputStream(filePath);
			
            //ファイルに書き込み
            stream.write(bytes);
            			
			// ファイルパスを戻す
			return DbPath;
						
		} catch (IOException e) {
			e.printStackTrace();
			
			// バイト型の変換に失敗した場合nullを返す
			return null;
		}	
	}
}
