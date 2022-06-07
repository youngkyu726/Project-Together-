package com.coding404.myweb.animal;

import java.io.File;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.coding404.myweb.command.HistoryVO;
import com.coding404.myweb.command.RecVO;
import com.coding404.myweb.command.animalVO;

import com.coding404.myweb.util.Criteria2;


@Service("AnimalService")
public class AnimalServiceImpl implements AnimalService {
	
	@Autowired
	private AnimalMapper animalMapper;

	//업로드 할 경로(application.properties값을 참조)
	@Value("${project.upload.path}")
	private String uploadpath;
	
	//폴더 생성 함수
	public String makeFolder() {
		
		//날짜별로 폴더생성
		DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyMMdd");
		String date = LocalDateTime.now().format(datetime);
		
		//java.io인 것을 import(업로드 경로 \\ 폴더명)
		File file = new File(uploadpath + "/" + date);
		
		if(file.exists() == false) { //폴더가 존재하면 true, 존재하지 않으면 false
			file.mkdir(); //폴더생성
			
		}
		
		return date; //년 , 월, 일 리턴
		
		
	}
	
	
	@Override
	public int regist(animalVO vo ,MultipartFile f) {
		
		
		
			//1.파일명 추출(브라우저 별로 다를수 있기 때문에 \\기준으로 파일명 추출)
			String originName = f.getOriginalFilename();
			String filename = originName.substring(originName.lastIndexOf("\\")+1); //미만 절삭
			
			//2.업로드 된 파일을 폴더별로 저장(파일생성)
			String filepath = makeFolder();
			
			//3.랜덤값을 이용해서 동일한 파일명의 처리
			String uuid = UUID.randomUUID().toString();
			
			//4.최종경로 설정
			String savename = uploadpath + "/" +filepath +"/" + uuid +"_" +filename;

			//5.업로드 진행
			try {
				f.transferTo(new File(savename));
			} catch (Exception e) {
				e.printStackTrace();
				return 0; //실패의 의미로 0을 리턴
			}
			
			//6. 빌더패턴을 이용해 업로드 테이블에 insert진행
			
			//ADMIN_NUM은 화면에서 전달되지 않기때문에 현재 사용할수 없다
			//FK가 누락되었으므로 에러가 발생하는데 mybatis의 
			
			vo.setANIMAL_FILENAME(filename);
			vo.setANIMAL_PATH(filepath);
			vo.setANIMAL_UUID(uuid);
			
			
		
			int result = animalMapper.regist(vo);
		
		return result;
	}


	
	
	@Override
	public ArrayList<animalVO> getdetail(Criteria2 cri) {
		
		
		return animalMapper.getdetail(cri);
	}


	@Override
	public animalVO modalview(String pk) {
		
		
		
		return animalMapper.modalview(pk);
	}


	@Override
	public int getTotal(Criteria2 cri) {
		
		return animalMapper.getTotal(cri);
	}


	@Override
	public ArrayList<RecVO> getrec(String User_id) {
		
		
		return animalMapper.getrec(User_id);
	}


	@Override
	public int insertHistory(HistoryVO vo) {
		return animalMapper.insertHistory(vo);
	}


	@Override
	public int deleteAnimal(String num) {

		return animalMapper.deleteAnimal(num);
	}


	@Override
	public animalVO getupdateselect(String num) {

		
		return animalMapper.getupdateselect(num);
	}


	@Override
	public int updateanimal(animalVO vo, MultipartFile f) {


		//1.파일명 추출(브라우저 별로 다를수 있기 때문에 \\기준으로 파일명 추출)
		String originName = f.getOriginalFilename();
		String filename = originName.substring(originName.lastIndexOf("\\")+1); //미만 절삭
		
		//2.업로드 된 파일을 폴더별로 저장(파일생성)
		String filepath = makeFolder();
		
		//3.랜덤값을 이용해서 동일한 파일명의 처리
		String uuid = UUID.randomUUID().toString();
		
		//4.최종경로 설정
		String savename = uploadpath + "/" +filepath +"/" + uuid +"_" +filename;

		//5.업로드 진행
		try {
			f.transferTo(new File(savename));
		} catch (Exception e) {
			e.printStackTrace();
			return 0; //실패의 의미로 0을 리턴
		}
		
		//6. 빌더패턴을 이용해 업로드 테이블에 insert진행
		
		//ADMIN_NUM은 화면에서 전달되지 않기때문에 현재 사용할수 없다
		//FK가 누락되었으므로 에러가 발생하는데 mybatis의 
		
		vo.setANIMAL_FILENAME(filename);
		vo.setANIMAL_PATH(filepath);
		vo.setANIMAL_UUID(uuid);
		
		
	
		int result = animalMapper.updateanimal(vo);
	
		return result;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
