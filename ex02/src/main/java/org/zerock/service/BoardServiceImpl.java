package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

	// spring 4.3 이상에서 자동 처리
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Override
	public void register(BoardVO board) {
		// 파라미터로 전달되는 BoardVO 타입의 객체를 BoardMapper를 통해서 처리함
		log.info("register......" + board);

		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) {

		// 게시물 번호가 파라미터. BoardVO 인스턴스가 리턴
		log.info("get~~~~~~~~~~~~~~~~~" + bno);

		return mapper.read(bno);
	}

	public boolean modify(BoardVO board) {
		
		log.info("modify ~~~~~~~~~~ " + board);
		
		return mapper.update(board) == 1;
	}

	public boolean remove(Long bno) {
		
		log.info("DELETE" + bno);
		
		return mapper.delete(bno) == 1;
	}

	/*
	 * public List<BoardVO> getList() {
	 * 
	 * log.info("getList .....");
	 * 
	 * return mapper.getList(); }
	 */
	
	public List<BoardVO> getList(Criteria cri) {
		
		log.info("getList with Criteria: " + cri);
		
		return mapper.getListWithPaging(cri);
	}
}
