function onButtonHref(button){
	href = button.getElementsByTagName("a");
	if(href.length <= 0){
		return
	}
	
	href[0].click()
}