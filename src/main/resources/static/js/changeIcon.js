function pushHideButton() {
	// id="textPassword"のタグを取得
	var txtPass = document.getElementById("textPassword");
	
	// id="buttonEye"のタグを取得
	var btnEye = document.getElementById("buttonEye");
	
	// typeがtextの場合
	if(txtPass.type == "text") {
		// text → passwordに変更
		txtPass.type = "password";
		
		// fa-solid fa-eye → fa-solid fa-eye-slashに変更
		btnEye.className="fa-solid fa-eye-slash px-2";
		
	//typeがpasswordの場合
	} else {
		// password → textに変更
		txtPass.type = "text";
		
		// fa-solid fa-eye-slash → fa-solid fa-eyeに変更
		btnEye.className = "fa-solid fa-eye px-2";
	}
}
