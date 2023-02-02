/**
 * empList.js
 */
//목록 출력
let totalAry = []; //전체 목록 담아놓을 용도
fetch("../empListJson ")
  .then((resolve) => resolve.json())
  .then((result) => {
    // console.log(result);
    localStorage.setItem("total", result.length);
    totalAry = result;
    //배열 관련 메소드 : forEach, map//a타입을 넣으면 b타입 리턴, filter//조건 만족하는것, reduce 메소드
    // result.forEach(function (item) {
    //   let tr = makeTr(item);
    //   list.append(tr);

    // });//result배열에 등록된 값의 갯수 만큼 function()실행
    document.querySelector("#pageCnt").dispatchEvent(chgEvent);
    //내방법
    // showPages(1);
    // employeeList(1);
  })

  .catch((reject) => {
    console.log(reject);
  });

//저장버튼에 submit 이벤트 등록.
document
  .querySelector('form[name="empForm"]')
  .addEventListener("submit", addMemberFnc);

//전체 선택 체크박스
document
  .querySelector('thead input[type="checkbox"]')
  .addEventListener("change", allCheckChange);

//선택 삭제 버튼
document
  .querySelector("#delselectedBtn")
  .addEventListener("click", deleteCheckedFnc);

//조회 할 리스트 갯수
let chgEvent = new Event("change");
document.querySelector("#pageCnt").addEventListener("change", function () {
  localStorage.setItem("pagePerCnt", this.value);

  showPages(1);
  employeeList(1);
});
//내방법
//document.querySelector("select").addEventListener("change", selChange);

//데이터 한 건 활용해서 tr 요소 생성
function makeTr(item) {
  //DOM 요소 생성.
  let titles = ["id", "lastName", "email", "hireDate", "job"];
  //데이터 건수 만큼
  let tr = document.createElement("tr");

  //columns 갯수 만큼 반복.
  titles.forEach(function (title) {
    let td = document.createElement("td");

    td.innerText = item[title];
    tr.append(td);
  });

  // console.log(tr);
  let td = document.createElement("td");
  let btn = document.createElement("button");
  btn.innerText = "삭제";
  btn.addEventListener("click", deleteRowFunc);
  td.append(btn);
  tr.append(td);

  td = document.createElement("td");
  btn = document.createElement("button");
  btn.innerText = "수정";
  btn.addEventListener("click", modityTrFunc);

  td.append(btn);
  tr.append(td);

  //체크박스
  td = document.createElement("td");
  let chk = document.createElement("input");
  chk.setAttribute("type", "checkbox");
  chk.addEventListener("change", checkAllFnc);
  chk.addEventListener("click", check);
  td.append(chk);
  tr.append(td);

  //tr 반환
  return tr;
}

function checkAllFnc() {
  //전체 건수 vs 선택건수 비교
  let allTr = document.querySelectorAll("tbody#list tr");
  let chkTr = document.querySelectorAll('tbody input[type="checkbox"]:checked');
  console.log(allTr, chkTr);
  if (allTr.length == chkTr.length) {
    document.querySelector('thead input[type="checkbox"]').checked = true;
  } else {
    document.querySelector('thead input[type="checkbox"]').checked = false;
  }
}

//전체 체크박스 해제
function check() {
  if (document.querySelector('thead input[type="checkbox"]').checked == true) {
    document.querySelector('thead input[type="checkbox"]').checked = false;
  }
}
//삭제버튼 이벤트 콜백 함수.
function deleteRowFunc() {
  // console.log(this.parentElement.parentElement.firstChild.innerText);
  let id = this.parentElement.parentElement.firstChild.innerText;

  //목록 삭제
  fetch("../empListJson?del_id=" + id, {
    method: "DELETE",
  })
    .then((resolve) => resolve.json())
    .then((result) => {
      if (result.retCode == "Success") {
        alert("정상적으로 삭제.");
        this.parentElement.parentElement.remove();
      } else if (result.retCode == "Fail") {
        alert("오류발생");
      }
    })

    .catch((reject) => console.log(reject));
}

//수정화면 함수.
function modityTrFunc() {
  //this => 수정버튼
  let tr = document.createElement("tr");
  let thisTr = this.parentElement.parentElement;
  console.log("사원번호:", thisTr.children[0].innerText);
  console.log("이름:", thisTr.children[1].innerText);
  console.log("??:", thisTr.children[2].innerText);
  let id = thisTr.children[0].innerText;
  let name = thisTr.children[1].innerText;
  let email = thisTr.children[2].innerText;
  let hire = thisTr.children[3].innerText;
  let job = thisTr.children[4].innerText;

  let newTr = document.createElement("tr");

  thisTr.replaceWith(newTr);

  //변경할 항목 배열에 등록.
  let modItems = [name, email, hire, job];

  //사원번호 변경
  let td = document.createElement("td");
  td.innerText = id; //변경 불가 항목.
  newTr.append(td);

  //item 변경
  modItems.forEach((item) => {
    td = document.createElement("td");
    let inp = document.createElement("input");
    inp.style = "width : 100px";
    inp.value = item;
    td.append(inp);
    newTr.append(td);
  });

  //삭제:비활성화, 변경 :DB반영
  let btn = document.createElement("button");
  btn.innerText = "삭제";
  btn.disabled = true;
  td = document.createElement("td");
  td.append(btn);
  newTr.append(td);

  //변경버튼.
  btn = document.createElement("button");
  btn.innerText = "변경";
  btn.addEventListener("click", updateMemberFnc);
  td = document.createElement("td");
  td.append(btn);
  newTr.append(td);

  td = document.createElement("td");
  let chk = document.createElement("input");
  chk.setAttribute("type", "checkbox");
  chk.addEventListener("click", check);
  td.append(chk);
  newTr.append(td);
}

//수정 처리 함수
function updateMemberFnc() {
  let currTr = this.parentElement.parentElement;
  let id = currTr.children[0].innerText;
  let name = currTr.children[1].children[0].value;
  let email = currTr.children[2].children[0].value;
  let hDate = currTr.children[3].children[0].value;
  let job = currTr.children[4].children[0].value;
  console.log(name);
  fetch("../empListJson", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body:
      "param=update&id=" +
      id +
      "&name=" +
      name +
      "&email=" +
      email +
      "&hire=" +
      hDate +
      "&job=" +
      job,
  })
    .then((resolve) => resolve.text())
    .then((result) => {
      if (result.indexOf("Success") != -1) {
        alert("정상 처리");
        let newTr = makeTr({
          id: id,
          lastName: name,
          email: email,
          hire: hDate,
          job: job,
        });
        currTr.replaceWith(newTr);
      } else {
        console.log("erro");
      }
    })
    .catch((reject) => console.log(reject));
}

//저장처리 함수
function addMemberFnc(event) {
  //preventDefault를 해 주지 않으면 기본값으로 다른 페이지로 넘어가기 때문에 지정해줌
  event.preventDefault();
  //event.preventDefault의 기능을 확인 하기 위한 console.log
  // console.log("여기에 출력");
  let id = document.querySelector('input[name="emp_id"]').value;
  let name = document.querySelector('input[name="last_name"]').value;
  let email = document.querySelector('input[name="email"]').value;
  let hDate = document.querySelector('input[name="hire_date"]').value;
  let job = document.querySelector('input[name="job_id"]').value;

  if (!id || !name || !email || !hDate || !job) {
    alert("필수입력값을 확인!!");
    return;
  }
  fetch("../empListJson", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" }, //파라메터로 key=value&key1=value1식으로 보낸다 //파일 보내는 형식지정.
    body:
      "param=&id=" +
      id +
      "&name=" +
      name +
      "&email=" +
      email +
      "&hire=" +
      hDate +
      "&job=" +
      job,
  })
    .then((resolve) => resolve.json())
    .then((result) => {
      if (result.retCode == "Success") {
        alert("정상처리");
        //makeTr 함수를 이용해서 html 태그를 생성하면서 출려하는 기능을 구동한다.(makeTr매개변수의 설정을 따른다)
        list.append(
          makeTr({
            id: id,
            lastName: name,
            email: email,
            hire: hDate,
            job: job,
          })
        );
        document.querySelector('input[name="emp_id"]').value = " ";
        document.querySelector('input[name="last_name"]').value = " ";
        document.querySelector('input[name="email"]').value = " ";
        document.querySelector('input[name="hire_date"]').value = " ";
        document.querySelector('input[name="job_id"]').value = " ";
      } else if (result.retCode == "False") {
        alert("처리중 에러!");
      }
    });
}

//전체 선택 체크 박스
function allCheckChange() {
  console.log(this.checked);
  //tbody에 있는 체크 박스 선택
  document.querySelectorAll('tbody input[type="checkbox"]').forEach((chk) => {
    chk.checked = this.checked;
  });
}
//선택 체크박스 삭제
//fetch API=>비동기방식처리 =>동기식 처리 (async,await);
async function deleteCheckedFnc() {
  let ids = [];
  let chks = document.querySelectorAll('tbody input[type="checkbox"]:checked');

  for (let i = 0; i < chks.length; i++) {
    //삭제처리 같은 기능을 구현해 보세요.
    let id = chks[i].parentElement.parentElement.firstChild.innerText;

    //목록 삭제
    let resp = await fetch("../empListJson?del_id=" + id, {
      method: "DELETE",
    });
    let json = await resp.json();
    console.log(json);
    ids.push(json);
  }
  // console.log("ids>>>", ids);

  processAtrerFetch(ids); //[{id:101,retcode:Success},{id:101,retcode:Success}...]
}

//화면 처리
function processAtrerFetch(ary = []) {
  let targetTr = document.querySelectorAll("#list tr");
  console.log(targetTr, "vs", ary);

  targetTr.forEach((tr) => {
    for (let i = 0; i < ary.length; i++) {
      if (tr.children[0].innerText == ary[i].id) {
        if (ary[i].retCode == "Success") {
          tr.remove(); //Success 조건 하에 삭제.
        } else {
          tr.setAttribute("class", "delError");
        }
      }
    }
  });
}

//페이지 목록
function showPages(curPage = 5) {
  //init
  document.querySelectorAll("#paging a").forEach((item) => item.remove());
  let cnt = document.querySelector("select").value;
  //전체 건수
  let totalCnt = parseInt(localStorage.getItem("total"));
  let curPerPage = parseInt(localStorage.getItem("pagePerCnt"));
  let endPage = Math.ceil(curPage / 10) * 10;
  let startPage = endPage - 9;
  //내방법
  // let realEnd = Math.ceil(totalCnt / cnt);
  let realEnd = Math.ceil(totalCnt / curPerPage);

  let prev, next; //이전 페이지 목록 /다음 페이지 목록  존재 확인

  endPage = endPage > realEnd ? realEnd : endPage;
  prev = startPage > 1 ? true : false;
  next = realEnd > endPage ? true : false;

  let paging = document.getElementById("paging");
  if (prev) {
    let aTag = document.createElement("a");
    aTag.addEventListener("click", showListPages);
    aTag.href = "#";
    aTag.page = startPage - 1;
    aTag.innerHTML = "&laquo"; //startPage -1
    paging.append(aTag);
  }
  for (let i = startPage; i <= endPage; i++) {
    let aTag = document.createElement("a");
    aTag.addEventListener("click", showListPages);
    aTag.href = "#";
    aTag.innerText = i;
    aTag.page = i; //innerText 속성이 페이지 값을 활용했지만
    if (i == curPage) {
      aTag.className = "active"; //aTag.setAttribute('class','active)
    }
    paging.append(aTag);
  }
  if (next) {
    let aTag = document.createElement("a");
    aTag.addEventListener("click", showListPages);
    aTag.href = "#";
    aTag.page = endPage + 1;
    aTag.innerHTML = "&raquo"; //endPage +1
    paging.append(aTag);
  }
}

//사원 목록
function employeeList(curPage = 5) {
  document.querySelectorAll("#list tr").forEach((item) => item.remove());
  let curPerPage = parseInt(localStorage.getItem("pagePerCnt"));

  let cnt = document.querySelector("select").value;
  //내방법
  // let end = curPage * cnt;
  // let start = end - (cnt - 1);
  let end = curPage * curPerPage;
  let start = end - (curPerPage - 1);
  let newList = totalAry.filter((emp, idx) => {
    return idx + 1 >= start && idx < end;
  });

  let lst = document.getElementById("list");
  newList.forEach((emp) => {
    let tr = makeTr(emp);
    lst.append(tr);
  });
}

//페이지 클릭히면 페이지 목록 & 사원 목록
//aTag에 들어간 이벤트 핸들러 이기 때문에 this는 aTag이다
function showListPages(e) {
  console.log(e);
  let page = e.target.page;

  showPages(page);
  employeeList(page);
  console.log(page);
}
//내방법
// function selChange() {
//   // console.log(cnt);
//   showPages(1);
//   employeeList(1);
// }

//this => function() {this =>widow전역객체}
//this => event 의 callback function() {this => event대상.}
