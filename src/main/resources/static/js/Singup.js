// //아이디 체크여부 확인 (아이디 중복일 경우 = 0 , 중복이 아닐경우 = 1 )
// let idck = 0;
//
// $(function () {
//     //idck 버튼을 클릭했을때
//     $("#idck").click(function () {
//         //userid 를 param
//         const userid = $("#id").val();
//
//         $.ajax({
//                 async: true,
//                 type: 'POST',
//                 data: id,
//                 url: "idcheck.do",
//                 dataType: "json",
//                 contentType: "application/json; charset=UTF-8",
//             success: function (data) {
//                     if (data.cnt > 0) {
//                         alert("아이디가 존재합니다. 다른 아이디를 입력해주세요")
//                         //아이디가 존재할 경우 빨강으로, 아니면 파랑으로 처리하는 디자인
//                         $("#divinputId").addClass("has-error")
//                         $("#divinputId").removeClass("has-success")
//                         $("#userid").focus();
//                     } else {
//                         alert("사용가능한 아이디입니다.");
//                         $("#divinputId").addClass("has-error")
//                         $("#divinputId").removeClass("has-success")
//                         $("#userid").focus();
//                         //아이디가 중복하지 않으면 idck = 1
//                         idck = 1;
//                         if(confirm("회원가입을 하시겠습니까?")){
//                             if(idck==0){
//                                 alert('아이디 중복체크를 해주세요');
//                                 return false;
//                             }else{
//                                 alert("회원가입을 축하합니다");
//                                 $("#frm").submit();
//                             }
//                         }
//                     }
//             },
//             error: function (error) {
//
//                     alert("error: " + error);
//             }
//             });
//         });
// });