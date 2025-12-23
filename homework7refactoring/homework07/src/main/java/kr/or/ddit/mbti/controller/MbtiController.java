package kr.or.ddit.mbti.controller;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.exception.DuplicatePkException;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.mbti.config.MbtiControllerConfig;
import kr.or.ddit.mbti.dto.MbtiDTO;
import kr.or.ddit.mbti.service.MbtiService;
import kr.or.ddit.util.MbtiControllerUtil;


@WebServlet("/mbti/*")
@MultipartConfig
public class MbtiController extends HttpServlet {
	
	MbtiControllerConfig mbtiConfig = new MbtiControllerConfig();
	
//	private final MbtiService mbtiService = new MbtiServiceImpl();
//	private MbtiControllerUtil controllerUtil = new MbtiControllerUtil();

	//책임분리를 위해 의존성 주입을 활용함
	//기존에는 mbtiService가 new MbtiServiceImpl()의 구현체를 의존하고있었음 완벽한 책임분리가 되지않음
	//이렇게하면 구현체가 바뀌더라도 이 코드를 건들일 필요가 없어짐. MbtiControllerConfig만 수정해주면됨
	private final MbtiService mbtiService = mbtiConfig.mbtiService();
	private final MbtiControllerUtil controllerUtil = mbtiConfig.mbtiControllerUtil();
	
	private final Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = controllerUtil.getMbtiType(req);
		Object result = (type == null) ? mbtiService.selectList() : mbtiService.selectOne(type);
		controllerUtil.sendJsonResponse(resp, result);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			MbtiDTO dto = controllerUtil.bindDto(req);
			controllerUtil.sendJsonResponse(resp, mbtiService.insert(dto));
		} catch (DuplicatePkException e) {
			resp.sendError(400, "이미 존재하는 mbti 입니다.");
		} catch (Exception e) {
			resp.sendError(500, "서버오류 관리자에게 문의 요망.");
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			BufferedReader reader = req.getReader();
		    MbtiDTO dto = new Gson().fromJson(reader, MbtiDTO.class);
			dto.setMtType(controllerUtil.getMbtiType(req)); // URL 경로의 타입을 DTO에 주입
			controllerUtil.sendJsonResponse(resp, mbtiService.update(dto));
		}catch (PkNotFoundException e) {
			resp.sendError(400, "해당하는 mbti가 존재하지 않습니다.");
		} 
		catch (Exception e) {
			resp.sendError(500, "서버오류 관리자에게 문의 요망.");
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			controllerUtil.sendJsonResponse(resp, mbtiService.delete(controllerUtil.getMbtiType(req)));
		}catch (PkNotFoundException e) {
			resp.sendError(400, "해당하는 mbti가 존재하지 않습니다.");
		} 
		catch (Exception e) {
			resp.sendError(500, "서버오류 관리자에게 문의 요망.");
		}
	}
}
