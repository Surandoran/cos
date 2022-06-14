// var idconfrim=document.getElementById("id_input");
// idconfrim.addEventListener('keyup', function(){
//     var xhr = new XMLHttpRequest(); //new로 생성
//     var value='id = ' + idconfrim.values;
//     xhr.open('POST', '/auth/idconfirm', true);//j쿼리 $ajax.({type,url},true가 비동기)
//     xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");//이게 없으면 post전송불가
//     xhr.send(value); // ajax data부분
//     xhr.onload = function (){
//         let color;
//         if(xhr.status == null) { //success:function(data)부분 통신 성공시 200반환
//             if (xhr.response == true) {
//                 color = 'blue';
//             } else {
//                 color = 'red';
//             }
//             idconfrim.style.background = color;
//         }
//     }
// })

// function check(){
//     id = $('#id').val();
//
//     $.ajax({
//         url: 'ID_Check',
//         type: 'POST',
//         dataType: 'text', //서버로부터 내가 받는 데이터의 타입
//         contentType : 'text/plain; charset = utf-8;', //내가 서버로 보내는 데이터의 타입
//         data: id,
//
//         success: function (data){
//             if(data == 0){
//                 console.log("아이디 없음");
//                 alert("사용하실 수 있는 아이디입니다.");
//             }else{
//                 console.log("아이디 있음");
//                 alert("중복된 아이디가 존재합니다.");
//             }
//         },
//         error: function (){
//
//         }
//     });
// }
$("#overlappedID").click(function(){
    $("#signup").attr("type", "button");
    const id = $("#user_id").val();
    $.ajax({
        type: "get",
        async: false,
        url: "http://localhost:8080/member/idCheck",
        data: {id: id},
        success: function (data) {
            if(data == 1) {
                $("#olmessage").text("이미 사용중인 ID 입니다.");
                $("#olmessage").addClass("olmessagef");
                $("#olmessage").removeClass("olmessaget");
            }else {
                $("#olmessage").text("사용 가능한 ID 입니다.");
                $("#olmessage").addClass("olmessaget");
                $("#olmessage").removeClass("olmessagef");
                $("#signup").attr("type", "submit");
            }
        }
    })
});