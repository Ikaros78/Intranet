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