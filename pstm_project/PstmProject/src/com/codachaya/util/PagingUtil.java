package com.codachaya.util;

public class PagingUtil {

	int recordsPerPage; //페이지당 레코드 수
	int firstPageNo;//첫번째 페이지 번호
	int prevPageNo; //이전 페이지 번호
	int startPageNo; //시작 페이지(페이징 너비 기준)
	int currentPageNo; //페이지 번호
	int endPageNo; // 끝 페이지(페이징 너비 기준)
	int nextPageNo; //다음 페이지 번호
	int finalPageNo; //마지막 페이지 번호
	int numberOfRecords; //전체 레코드 수
	int sizeOfPage;//1,2,3,4,5
	boolean isNowFirst; //현제 페이지 첫번째 여부
	boolean isNowFinal; //현재 페이지 마지막 여부
	int startPage; //startPageno에 들어갈 값
	int endPage;//finalPageno에 들어갈 값
	
	public PagingUtil(int currentPageNo, int recordsPerPage) {
		
		this.currentPageNo = currentPageNo;
		this.sizeOfPage = 10;
		this.recordsPerPage = (recordsPerPage != 0) ? recordsPerPage : 3;
		
	}
	
	
	public int getRecordsPerPage() {
		return recordsPerPage;
	}
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	public int getFirstPageNo() {
		return firstPageNo;
	}
	public void setFirstPageNo(int firstPageNo) {
		this.firstPageNo = firstPageNo;
	}
	public int getPrevPageNo() {
		return prevPageNo;
	}
	public void setPrevPageNo(int prevPageNo) {
		this.prevPageNo = prevPageNo;
	}
	public int getStartPageNo() {
		return startPageNo;
	}
	public void setStartPageNo(int startPageNo) {
		this.startPageNo = startPageNo;
	}
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public int getNextPageNo() {
		return nextPageNo;
	}
	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}
	public int getFinalPageNo() {
		return finalPageNo;
	}
	public void setFinalPageNo(int finalPageNo) {
		this.finalPageNo = finalPageNo;
	}
	public int getNumberOfRecords() {
		return numberOfRecords;
	}
	public void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}
	public int getSizeOfPage() {
		return sizeOfPage;
	}
	public void setSizeOfPage(int sizeOfPage) {
		this.sizeOfPage = sizeOfPage;
	}
	
	public int getEndPageNo() {
		return endPageNo;
	}


	public void setEndPageNo(int endPageNo) {
		this.endPageNo = endPageNo;
	}


	public void makePaging() {
		
		//게시글 전체 수가 없는 경우
		if(numberOfRecords == 0) {
			return;
		}
		
		if(currentPageNo == 0) {
			//기본 값 설정
			setCurrentPageNo(1);
		}
		
		if(recordsPerPage == 0) {
			
			setRecordsPerPage(10);
			
		}
		
		//마지막 페이지 
		// 2 = (6 + (3-2)) / 3
		int finalPage = (numberOfRecords + (recordsPerPage - 1))/recordsPerPage;
	
		//현재 페이지 넘버(뒤로 계속 넘겼을 떄 최대 마지막 페이지에 있도록 한다)
		if(currentPageNo > finalPage) {
			
			setCurrentPageNo(finalPage);
			
		}
	
		// 페이지 유효성 체크
		if(this.currentPageNo <0 || this.currentPageNo > finalPage) {
			currentPageNo = 1;
			
		}
		
		//첫번째/마지막 페이지 여부
		isNowFirst = currentPageNo == 1 ? true: false;
		isNowFinal = currentPageNo == finalPage ? true:false;
		
		//전체 페이지 시작과 끝
		startPage = ((currentPageNo - 1)/sizeOfPage) * sizeOfPage + 1;
		endPage = startPage + sizeOfPage - 1;
		
		
		
		if(endPage > finalPage) {
			endPage = finalPage;
			
			
		}
		
		setFirstPageNo(1);
		
		
		//첫번째 페이지이면 
		//이전 페이지를 1로 세팅, 
		//아니면 현재 페이지 이전 페이지로 세팅
		if(isNowFirst) {
			setPrevPageNo(1);
		}else {
			setPrevPageNo(((currentPageNo -1) < 1 ? 1: (currentPageNo - 1)));
		}
		
		setStartPageNo(startPage);
		setEndPageNo(endPage);
		
		//현재가 마지막 페이지라면
		//다음 페이지를 마지막페이지로 설정
		//이니면 현재 페이지 다음페이지로 설정
		if(isNowFinal) {
			setNextPageNo(finalPage);
		}else {
			setNextPageNo(((currentPageNo + 1)> finalPage ? finalPage : (currentPageNo + 1)));
		}
		setFinalPageNo(finalPage);
		
	}

}
