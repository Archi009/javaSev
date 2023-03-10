/**
 *  manage.js
 */
//console.log("여기요");

$(document).ready(function () {
  let clone = $("#template").clone(true);
  console.log(clone.find("tr"));
  let tr = clone.find("tr");
  tr.find(".name").val("test");
  $("#list").append(tr); //복사!!!

  //목록가져오는 Ajax 호출.
  console.log($("#list"));
  $.ajax({
    url: "memberList.do",
    success: function (result) {
      // console.log(result);
      $(result).each(function (index, item) {
        // console.log(index, item);
        $("#list").append(makeRow(item));
      });
    },
    error: function (reject) {
      console.log(reject);
    },
  });

  //등록 이벤트
  $("#addBtn").on("click", function () {
    console.log("처리됨?");
    let id = $("#mid").val(); //element.value 속성을 읽어옴.
    let name = $("#mname").val();
    let phone = $("#mphone").val();
    let addr = $("#maddr").val();
    let image = $("#mimage")[0].files[0];

    let formData = new FormData();
    formData.append("id", id);
    formData.append("name", name);
    formData.append("phone", phone);
    formData.append("addr", addr);
    formData.append("image", image);

    $.ajax({
      url: "addMember.do",
      method: "post",
      data: formData,
      contentType: false,
      processData: false,
      success: function (result) {
        //처리된 정보를 화면 생성.
        console.log(result);
        if (result.retCode == "Success") {
          $("#list").append(makeRow(result.member));
        } else {
          alert("not enough data");
        }
      },
      error: function (reject) {
        console.log(reject);
      },
    });
  });

  //매개 변수 member의 기본값은 객체 타입
  function makeRow(member = {}) {
    let tr = $("<tr />"); //document.createElement('tr');
    tr.on("dblclick", function (e) {
      console.log("ddd");
      let id = $(this).children().eq(0).text();
      let name = $(this).children().eq(1).text();
      let phone = $(this).children().eq(2).text();
      let addr = $(this).children().eq(3).text();
      let resp = $(this).children().eq(4).text();

      let nTr = $("<tr/>").append(
        $("<td/>").text(id),
        $("<td/>").append($("<input class='name'/>").val(name)),
        $("<td/>").append($("<input class='phone'/>").val(phone)),
        $("<td/>").append($("<input class='addr' />").val(addr)),
        $("<td/>").append($("<input class='resp'/>").val(resp)),
        $("<td/>").append(
          $(
            "<button onclick='updateMemberFnc(event)' class='btn btn-success'>수정</button>"
          )
        )
      );
      //ㅐ로운 tr로 기존tr대신.
      tr.replaceWith(nTr);
    });
    tr.append(
      $("<td />").text(member.memberId),
      $("<td />").text(member.memberName),
      $("<td />").text(member.memberPhone),
      $("<td />").text(member.memberAddr),
      $("<td />").text(member.responsibility),
      $("<td />").append(
        $("<button class='btn btn-danger'>삭제</button>")
          .attr("mid", member.memberId)
          .on("click", deleteMemberFnc)
      )
    );
    return tr;
  }
}); //문서가 다운로드 되에 다 읽어들인 다음 함수 실행 해라 why? javascript 는 비동기 식이라서
function deleteMemberFnc(e) {
  if (!window.confirm("삭제하시겠습니까?")) {
    return;
  }

  let user = $(e.target).attr("mid");

  $.ajax({
    url: "removeMember.do",
    data: { id: user }, //removeMember.do?id=user
    success: function (result) {
      if (result.retCode == "Success") {
        $(e.target).parent().parent().remove();
      } else {
        alert("삭제오류!!");
      }
    },
    error: function (reject) {
      console.log(reject);
    },
  });
} //endof deleteFnc

function updateMemberFnc(e) {
  //modifyMember.do 사용자 정보 수정.
  let tr = $(e.target).parent().parent(); //tr
  let id = tr.children().eq(0).text();
  // console.log(tr.find("td:nth-of-type(2) input").val()); //아래방법과 같다
  // console.log(tr.children().eq(2).children().eq(0).val());
  // console.log(tr.find("input.addr").val());
  // console.log(tr.children().eq(4).children().eq(0).val());
  let name = tr.find("input.name").val();
  let phone = tr.find("input.phone").val();
  let addr = tr.find("input.addr").val();
  let resp = tr.find("input.resp").val();

  $.ajax({
    url: "modify.do",
    data: { mid: id, mname: name, mphone: phone, maddr: addr, resp: resp },
    method: "post",
    success: function (result) {
      console.log(result);
      alert("수정완료");
      window.location.reload();
    },
    error: function (reject) {
      console.log(reject);
    },
  });
} //end of updateMemberFnc
