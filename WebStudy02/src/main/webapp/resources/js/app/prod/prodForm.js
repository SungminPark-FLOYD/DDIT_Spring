/**
 * 
 */
$(function() {
	
	
	const $prodBuyer = $('[name="prodBuyer"]');
	$('[name="prodLgu"]').on("change", function() {
		let lgu = $(this).val();
		$prodBuyer.find(`option`).each(function(index, opt) {
			
			if(index == 0 || !lgu || $(opt).hasClass(lgu)){
				$(opt).show();
			}else{
				$(opt).hide();
			}
		});
		$prodBuyer.find('option:first').prod('selected', true);
	})
	
	//모든 input태그 셀렉팅
	$(":input[data-init-value]").each(function(index, ipt) {
		let initValue = $(ipt).data("initValue");
		$(ipt).val(initValue);
		$(ipt).trigger("change");
	})
});