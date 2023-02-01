package com.yedam.java.practice;

import java.util.List;
import java.util.Scanner;


public class ManMain {
	public static void main(String[] args) {
		Managing ma = new Managing();
		Custumer co = new Custumer();
		Login lo = new Login();
		Scanner sc = new Scanner(System.in);
		String id = null;
		int selNum = 0;
		String name = null;
		String uId = null;
		String ps = null;
		String ad = null;
		String ph = null;
		String cl = null;
		String gr = null;
		System.out.println("=====수영장 관리 프로 그램====");
		System.out.println();
		while (true) {
			System.out.println("==1)로그인 | | 2)회원가입 ==");
			selNum = sc.nextInt();
		if(selNum == 1) {
			System.out.println("로그인");
			System.out.println("아이디 입력>>");
			uId = sc.next();
			System.out.println("비밀번호 입력>>");
			ps= sc.next();
			ManVO emp1 = lo.getPs(uId);
			System.out.println(emp1);
			if (emp1 == null) {
				System.out.println("아이디 혹은 비밀 번호가 잘못되었습니다.");
				return;
			}
			if(emp1.getUserPs().equals(ps)) {
				id = uId;
				System.out.println("로그인 성공");	
				break;
			}
		}else if(selNum==2) {	
			//회원가입
			System.out.println("아이디 입력>>");
			uId = sc.next();
			sc.nextLine();
			System.out.println("아이디 중복 검사");
			ManVO emp = lo.getId(uId);
			System.out.println(emp);
			//아이디 중복 검사
			if (emp == null) {
				System.out.println("사용가능한 아이디 입니다.");
				
			
			}else {
					System.out.println("중복된 아이디");	
					continue;
				}
			
			System.out.println("비밀번호 입력>>");
			ps=sc.nextLine();
			System.out.println("이름 입력>>");
			name = sc.nextLine();
			System.out.println("주소 입력>>");
			ad = sc.nextLine();
			System.out.println("연락처 입력>>");
			ph = sc.nextLine();
			
			ManVO emp1 = new ManVO();
			emp1.setUserId(uId);
			emp1.setUserPs(name);
			emp1.setUserNm(name);
			emp1.setUserAd(ad);
			emp1.setUserPh(ph);

			if (lo.addLo(emp1) > 0) {
				System.out.println("회원가입!!");
				continue;
			} else {
				System.out.println("처리중 에러!");
			}
			
		}
		}
//	
////		id = "관리자";
//		id = "고객";
		if (id.equals("root")) {
			while (true) {
				// 관리자
				System.out.println("========================================================================================");
				System.out.println("==| 1.전체 회원 조회 | 2.단일 회원 조회| 3.회원 등급 작성 | 4.수업 등록/삭제 | 5.회원 삭제 | 6.로그아웃==");
				System.out.println("========================================================================================");
				selNum = sc.nextInt();

				if (selNum == 1) {
					// 전체 회원 조회
					List<ManVO> list = ma.manVoList();
					for (ManVO emp : list) {
						System.out.println(emp.toStringr());
					}

				} else if (selNum == 2) {
					// 단일 회원 조회
					System.out.println("조회 할 회원 입력>>");
					name = sc.next();
					ManVO emp = ma.getMan(name);
					if (emp == null) {
						System.out.println("조회된 정보 없음!!");
						return;
					}
					System.out.println(name + emp.toStringManagerSearch());

				} else if (selNum == 3) {
					//회원 등급 입력
					System.out.println("회원명 입력");
					name = sc.next();
					System.out.println("등록/수정 할 등급 선택");
					System.out.println("1)선수 || 2)일반 || 3)초보");
					selNum = sc.nextInt();
					if(selNum == 1) {
						cl = "선수";						
					}else if(selNum == 2) {
						cl = "일반";						
					}else if(selNum == 3) {
						cl = "초보";						
					}						
					if (ma.updateCl(name, cl) > 0) {
						System.out.println(name + "님" + cl + "등급 설정");
					} else {
						System.out.println("처리중 에러!");
					}

				} else if (selNum == 4) {
					// 수업 등록/ 삭제
					System.out.println("회원명 입력");
					name = sc.next();
					System.out.println("1.수업 등록/수정 || 2.삭제");
					selNum = sc.nextInt();
					if (selNum == 1) {
						System.out.println("등록/수정 할 수업 입력");
						gr = sc.next();
						if (ma.updateGr(name, gr) > 0) {
							System.out.println(name + "님" + gr + "수업 등록/수정");
						} else {
							System.out.println("처리중 에러!");
						}
					} else if (selNum == 2) {
						if (ma.delGr(name) > 0) {
							System.out.println(name + "님 수업 삭제");
						} else {
							System.out.println("처리중 에러");
						}
					}

				} else if (selNum == 5) {
					// 회원 삭제
					System.out.println("삭제 할 회원명 입력>>");
					name = sc.next();
					System.out.println("삭제 할 회원 아이디 입력>>");
					uId = sc.next();
					if (ma.delMan(name, uId) > 0) {
						System.out.println("삭제 성공!!");
					} else {
						System.out.println("처리중 에러");
					}

				} else if (selNum == 6) {
					id = null;
					System.out.println("로그아웃");
					break;
				}

			}
			
			
			
			
			
			
			
		} else if (id!="root"){
			while (true) {
				// 고객
				System.out.println("================================================================================================");
				System.out.println("==| 1.전체 회원 조회 | 2.내 정보 조회 | 3.반 별 조회 | 4.등급 신청 | 5.수업 등록/취소 | 6.회원 탈퇴 | 7.로그아웃==");
				System.out.println("================================================================================================");
				selNum = sc.nextInt();

				if (selNum == 1) {
					// 전체 회원 조회
					List<ManVO> list = co.manVoListForCt();
					for (ManVO emp : list) {
						System.out.println(emp.toStringCuSearch());
					}

				} else if (selNum == 2) {
						//내 정보 조회
						ManVO name1 = co.getCuNm(id);
						ManVO emp = co.getSelfForCt(id);
						if (emp == null) {
							System.out.println("조회된 정보 없음!!");
							return;
						}
						System.out.println(name1.getUserNm() + emp.toStringCuSelfSearch());
					

				}else if (selNum == 3) {
					//반별 조회
					System.out.println("1.자유형||2.배영||3.평영||4.접영");
					if(sc.nextInt()==1) {
						gr = "자유형";						
					}else if(sc.nextInt()==2) {
						gr = "배영";						
					}else if(sc.nextInt()==3) {
						gr = "평영";						
					}else if(sc.nextInt()==4) {
						gr = "접영";						
					}else {
						System.out.println("유효한 값이 아닙니다.");
						}
					
					List<ManVO> list = co.getManForCt(gr);
					for (ManVO emp : list) {
						System.out.println(emp.toStringCuSearch());
					}
				} else if (selNum == 4) {
					ManVO name1 = co.getCuNm(id);
					System.out.println("신청 할 등급 입력");
					cl = sc.next();
					if (co.updateClForCt(id, cl) > 0) {
						System.out.println(name1.getUserNm() + "님" + cl + "등급 설정");
					} else {
						System.out.println("처리중 에러!");
					}

				} else if (selNum == 5) {
					// 수업 등록/ 삭제
					
					System.out.println("1.등록/수정 || 2.삭제");
					selNum = sc.nextInt();
					ManVO name1 = co.getCuNm(id);
					if (selNum == 1) {
						System.out.println("등록/수정 할 수업 입력");
						gr = sc.next();
						if (co.updateGrForCt(id, gr) > 0) {
							System.out.println(name1.getUserNm() + "님" + gr + "수업 등록/수정");
						} else {
							System.out.println("처리중 에러!");
						}
					} else if (selNum == 2) {
						if (co.delGrForCt(id) > 0) {
							System.out.println(name1.getUserNm() + "님 수업 삭제");
						} else {
							System.out.println("처리중 에러");
						}

					}

				} else if (selNum == 6) {
					// 회원 탈퇴
					System.out.println("삭제 할 회원명 입력>>");
					name = sc.next();
					System.out.println("비밀 번호 입력>>");
					ps = sc.next();
					ManVO emp = co.getPsForCt(id);
					if (emp == null) {
						System.out.println("잘못 된 비밀번호!");
						return;
					}
					
					if(emp.getUserPs().equals(ps)) {
					if (co.delManForCt(name, id) > 0) {
						System.out.println("탈퇴 성공!!");
						break;
					} else {
						System.out.println("처리중 에러");
					}
					}

				} else if (selNum == 7) {
					//로그아웃
					id = null;
					System.out.println("로그아웃");
					break;
				}

			}
//			
//			
//			
//			
//		}

		}
	}
}
