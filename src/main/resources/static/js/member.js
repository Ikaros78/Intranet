//아이디 유효성 검사
let userId = document.querySelector("#mem_id");
userId.addEventListener("change", (e) => {
  validId(e.target);
});
function validId(obj) {
  console.log(obj);
  if (validIdCheck(obj) == false) {
    alert("4~12자의 영문 대소문자와 숫자로 입력해주세요.");
    obj.value = "";
    obj.focus();
    return false;
  }
}
function validIdCheck(obj) {
  let pattern = /^[a-zA-Z0-9]{4,12}$/;
  return obj.value.match(pattern) != null;
}

//비밀번호 유효성 검사
let userPw = document.querySelector("#memPw");
userPw.addEventListener("change", (e) => {
  vaildPw(e.target);
});
function vaildPw(obj) {
  console.log(obj);
  if (vaildPwCheck(obj) == false) {
    alert("비밀번호는 8~20자리 숫자/문자/특수문자를 포함해야 합니다.");
    obj.value = "";
    obj.focus();
    return false;
  }
}
function vaildPwCheck(obj) {
  let pattern = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
  return obj.value.match(pattern) != null;
}

/**비밀번호 보이기, 안보이기 기능 */
$("#keyShow").on("click", function () {
  if ($("#memPw").attr("type") == "password") {
    $("#memPw").attr("type", "text");
    $($(this)).text("SHOW password");
  } else {
    $("#memPw").attr("type", "password");
    $($(this)).text("HIDE password");
  }
});

//   아이디 중복체크 기능

    	if(document.getElementById("checkDup")){

            const $dupCheck = document.getElementById("checkDup");

            $dupCheck.onclick = function (){
                let mem_id = document.getElementById("mem_id").value.trim();

                fetch("/member/checkDupId",{
                    method:"POST",
                    headers: {
                        'Content-Type' : 'application/json;charset-UTF-8'
                    },
                    body: JSON.stringify({mem_id: mem_id})
                })
                    .then(result => result.text())
                    .then(result => alert(result))
                    .catch((error) => error.text().then((res) => alert(res)));
            }
        }


//이름 유효성 검사
let userName = document.querySelector("#memName");
userName.addEventListener("change", (e) => {
  validName(e.target);
});
function validName(obj) {
  console.log(obj);
  if (validNameCheck(obj) == false) {
    alert("한글 2글자이상 5글자 이하/영문 4글자 이상 입력하세요.");
    obj.value = "";
    obj.focus();
    return false;
  }
}
function validNameCheck(obj) {
  let pattern = /^[가-힣]{2,5}$|[a-zA-Z]{4,}$/;
  return obj.value.match(pattern) != null;
}

//휴대폰 번호 유효성 검사
let userPhone = document.querySelector("#memPhone");
userPhone.addEventListener("change", (e) => {
  validPhone(e.target);
});
function validPhone(obj) {
  console.log(obj);
  if (validPhoneCheck(obj) == false) {
    alert("올바른 번호를 입력하세요.");
    obj.value = "";
    obj.focus();
    return false;
  }
}
function validPhoneCheck(obj) {
  let pattern = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
  return obj.value.match(pattern) != null;
}

// 전화번호 입력 시 하이픈 넣는 코드
const autoHypen = (target) => {
  target.value = target.value
    .replace(/[^0-9]/g, "")
    .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3")
    .replace(/(\-{1,2})$/g, "");
};

// //  생년월일 입력시 하이픈을 넣는 코드
// const autoHypen2 = (target) => {
//   target.value = target.value
//     .replace(/[^0-9]/g, "")
//     .replace(/^(\d{0,4})(\d{0,2})(\d{0,2})$/g, "$1-$2-$3")
//     .replace(/(\-{1,2})$/g, "");
// };

//회원가입 selectBox 값을 Input 박스에 넣는 코드
let selectBoxChange = function (value) {
  console.log(value);
  $("#memDept").val(value);
};

// 회원가입 우편번호 API
function sample6_execDaumPostcode() {
  new daum.Postcode({
    oncomplete: function (data) {
      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

      // 각 주소의 노출 규칙에 따라 주소를 조합한다.
      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
      var addr = ""; // 주소 변수
      var extraAddr = ""; // 참고항목 변수

      //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
      if (data.userSelectedType === "R") {
        // 사용자가 도로명 주소를 선택했을 경우
        addr = data.roadAddress;
      } else {
        // 사용자가 지번 주소를 선택했을 경우(J)
        addr = data.jibunAddress;
      }

      // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
      if (data.userSelectedType === "R") {
        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
        if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
          extraAddr += data.bname;
        }
        // 건물명이 있고, 공동주택일 경우 추가한다.
        if (data.buildingName !== "" && data.apartment === "Y") {
          extraAddr +=
            extraAddr !== "" ? ", " + data.buildingName : data.buildingName;
        }
        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
        if (extraAddr !== "") {
          extraAddr = " (" + extraAddr + ")";
        }
        // 조합된 참고항목을 해당 필드에 넣는다.
        document.getElementById("sample6_extraAddress").value = extraAddr;
      } else {
        document.getElementById("sample6_extraAddress").value = "";
      }

      // 우편번호와 주소 정보를 해당 필드에 넣는다.
      document.getElementById("sample6_postcode").value = data.zonecode;
      document.getElementById("memAddre").value = addr;
      // 커서를 상세주소 필드로 이동한다.
      document.getElementById("sample6_detailAddress").focus();
    },
  }).open();
}
