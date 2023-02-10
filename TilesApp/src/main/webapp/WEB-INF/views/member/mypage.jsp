<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

<h3>현재페이지는 myPageForm.do의 결과 mypage.jsp입니다</h3>
<h3>"${id }"의 개인정보 페이지 입니다.</h3>
<form action="modify.do" method="post">
  <input
    type="file"
    id="fileUpload"
    accept="image/*"
    style="display: none"
    onchange="imageChangeFnc()"
  />
  <table class="table">
    <tr>
      <th>아이디</th>
      <td><input type="text" name="mid" value="${vo.memberId }" readolny /></td>
    </tr>
    <tr>
      <th>이름</th>
      <td><input type="text" name="mname" value="${vo.memberName }" /></td>
    </tr>
    <tr>
      <th>비밀번호</th>
      <td><input type="text" name="mpass" value="${vo.memberPw }" /></td>
    </tr>
    <tr>
      <th>연락처</th>
      <td><input type="text" name="mphone" value="${vo.memberPhone }" /></td>
    </tr>
    <tr>
      <th>주소</th>
      <td><input type="text" name="maddr" value="${vo.memberAddr }" /></td>
    </tr>
    <tr>
      <th>image</th>
      <td><img id="imgSrc" width="300px" height="200" src="upload/${vo.image }" /></td>
    </tr>
    <tr>
      <td colspan="2" align="center"><input type="submit" value="수정" /></td>
    </tr>
  </table>
</form>
<script>
  //	event 등록 : addEventListener(type,function(){}) =>js
  //	elem.on('click',function(){}) =>jquery
  $("#imgSrc").on("click", function () {
    $("#fileUpload").click();
  });

  function imageChangeFnc() {
    console.log($("#fileUpload")[0].files[0]);
    let file = $("#fileUpload")[0].files[0];

    let formData = new FormData(); // multipart 요청 처리 객체.
    formData.append("id", "${vo.memberId }"); // id, file 업로드 : db변경.
    formData.append("image", file);
    //서버에 multipart/form-data : ajax 요청
    $.ajax({
      url: "imageUploade.do",
      method: "post",
      data: formData,
      contentType: false, //multipart 요청일 경우에 옵션.
      processData: false, //multipart 요청일 경우에 옵션.
      success: function (result) {
        console.log(result);
        //화면에서도 선택된 이미지가 보여줌.
        let reader = new FileReader();
        reader.onload = function (evnt) {
          console.log(evnt.target);
          $("#imgSrc").attr("src", evnt.target.result);
        };
        reader.readAsDataURL(file);
      },
      error: function (err) {
        console.log(err);
      },
    });
  }
</script>
