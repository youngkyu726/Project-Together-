//파일업로드
$(document).ready(function() {

	var fileTarget = $('.filebox .upload-hidden'); //jquery는 다중 태그이벤트도 한번에 처리
	fileTarget.on('change', function(){ //change이벤트
	if(window.FileReader){ // modern browser 
		var filename = $(this)[0].files[0].name; } 
	else { // old IE 
		var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
	} // 추출한 파일명 삽입 
	$(this).siblings('.upload-name').val(filename); });

	var imgTarget = $('.preview-image .upload-hidden'); 
	imgTarget.on('change', function() { 
		var parent = $(this).parent(); 
		// parent.children('.upload-display').remove(); 
		
		if(window.FileReader){ //image 파일만 
			if (!$(this)[0].files[0].type.match(/image\//)) return; 

			var reader = new FileReader(); 
			reader.onload = function(e){ 
				var src = e.target.result; 
				// parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img src="'+src+'" class="upload-thumb"></div></div>'); 
				parent.find(".upload-thumb-wrap").children().attr("src", src);
			} 
			reader.readAsDataURL($(this)[0].files[0]); 
		} else { 
			$(this)[0].select(); 
			$(this)[0].blur(); 
			var imgSrc = document.selection.createRange().text; 
			parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img class="upload-thumb"></div></div>'); 

			var img = $(this).siblings('.upload-display').find('img'); 
			img[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src=\""+imgSrc+"\")"; 
		} 
	});

});
