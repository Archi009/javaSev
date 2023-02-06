/**
 *
 */

// bookList배열을 활용해서 처리하도록 하세요.
let bookList = [
  {
    bookCode: "P0206001",
    bookTitle: "이것이자바다",
    bookAuthor: "홍성문",
    bookPress: "신흥출판사",
    bookPrice: "20000",
  },
  {
    bookCode: "P0206002",
    bookTitle: "이것이자바스크립트다",
    bookAuthor: "홍영웅",
    bookPress: "우리출판사",
    bookPrice: "25000",
  },
];
checkSave.addEventListener("click", addMemberFnc);
allcheck.addEventListener("change", allCheckChange);
checkDel.addEventListener("click", deleteCheckedFnc);
//화면 출력
bookList.forEach((item) => {
  let tr = makeTr(item);
  list.append(tr);
});
function makeTr(item) {
  //DOM 요소 생성.
  let titles = [
    "bookCode",
    "bookTitle",
    "bookAuthor",
    "bookPress",
    "bookPrice",
  ];
  //데이터 건수 만큼
  let tr = document.createElement("tr");

  let td = document.createElement("td");
  let chk = document.createElement("input");
  chk.setAttribute("type", "checkbox");
  // chk.addEventListener("change", checkAllFnc);
  // chk.addEventListener("click", check);
  td.append(chk);
  tr.append(td);

  //columns 갯수 만큼 반복.
  titles.forEach(function (title) {
    let td = document.createElement("td");

    td.innerText = item[title];
    tr.append(td);
  });

  // console.log(tr);
  td = document.createElement("td");
  let btn = document.createElement("button");
  btn.innerText = "삭제";
  btn.addEventListener("click", deleteRowFunc);
  td.append(btn);
  tr.append(td);

  return tr;
}

//저장
function addMemberFnc() {
  let bookCode = document.querySelector('input[id="bookCode"]').value;
  let bookTitle = document.querySelector('input[id="bookName"]').value;
  let bookAuthor = document.querySelector('input[id="author"]').value;
  let bookPress = document.querySelector('input[id="press"]').value;
  let bookPrice = document.querySelector('input[id="price"]').value;
  console.log(bookPress);
  if (!bookCode || !bookTitle || !bookAuthor || !bookPress || !bookPrice) {
    alert("필수입력값을 확인!!");
    return;
  }
  list.append(
    makeTr({
      bookCode: bookCode,
      bookTitle: bookTitle,
      bookAuthor: bookAuthor,
      bookPress: bookPress,
      bookPrice: bookPrice,
    })
  );
  console.log(bookPress);
  document.querySelector('input[id="bookCode"]').value = " ";
  document.querySelector('input[id="bookName"]').value = " ";
  document.querySelector('input[id="author"]').value = " ";
  document.querySelector('input[id="press"]').value = " ";
  document.querySelector('input[id="price"]').value = " ";
}

//삭제
function deleteRowFunc() {
  this.parentElement.parentElement.remove();
}

//전체선택
function allCheckChange() {
  console.log(this.checked);
  //tbody에 있는 체크 박스 선택
  document.querySelectorAll('tbody input[type="checkbox"]').forEach((chk) => {
    chk.checked = this.checked;
  });
}

//선택 박스 삭제
//선택 체크박스 삭제
//fetch API=>비동기방식처리 =>동기식 처리 (async,await);
function deleteCheckedFnc() {
  let ids = [];
  let chks = document.querySelectorAll('tbody input[type="checkbox"]:checked');

  for (let i = 0; i < chks.length; i++) {
    let id = chks[i].parentElement.parentElement;
    id.remove();
    //목록 삭제
  }
}
