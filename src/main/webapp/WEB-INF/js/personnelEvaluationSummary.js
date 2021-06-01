$(function() {
	let selectYear;
	let selectDepart;
	let nowFiscalYear = parseInt($("#nowFiscalYear").val());

	$(document).ready(function() {
		$("#year").find("option").each(function() {
			let fiscalYear = $(this).data("val");
			if (nowFiscalYear === fiscalYear) {
				$(this).html("<option value=" + nowFiscalYear + ">&emsp;***年度を選択してください***</option>");
				$(this).prop('selected', true);
			}
		});
		selectYear = parseInt($("#year").val());

		selectedDepartList();

		selectDepart = $("#depart").val();

		selectedSummaryList();

		personnelPeriod();

		selectedPositionList();

		selectedTargetDate();

		buttonChenge();

		radioButton();

		totalRank();

		totalVariableRank();

		inputJudgment();
	});

	$("#year").on("change", function() {
		selectYear = parseInt($(this).val());

		selectedDepartList();

		selectedSummaryList();

		selectedPositionList();

		selectedTargetDate();

		buttonChenge();

		totalRank();

		totalVariableRank();
	});

	$("#depart").on("change", function() {
		selectDepart = $(this).val();

		selectedSummaryList();

		totalVariableRank();
	});

	$(".selectLastPositionCode").on("change", function() {
		let selectPosition = $(this).find("option:selected");
		let selectedLastRank = parseInt($(this).closest(".summaryData").find(".lastRank").text());
		if (selectPosition.prev().length === 0) {
			$(this).closest(".summaryData").find(".selectLastRank").val(0);
		} else if (selectPosition.next().length > 0) {
			if (selectPosition.data("maxrank") === selectPosition.next().data("maxrank")) {
				if (selectedLastRank <= selectPosition.data("maxrank")
						&& selectedLastRank > selectPosition.next().next().data("maxrank")) {
					$(this).closest(".summaryData").find(".selectLastRank").val(selectedLastRank);
				} else {
					$(this).closest(".summaryData").find(".selectLastRank").val(
							parseInt(selectPosition.next().next().data("maxrank")) + 10);
				};
			} else if(selectPosition.data("maxrank") === selectPosition.prev().data("maxrank")
					&& selectedLastRank <= selectPosition.data("maxrank")
					&& selectedLastRank > selectPosition.next().data("maxrank")) {
					$(this).closest(".summaryData").find(".selectLastRank").val(selectedLastRank);
			} else {
				$(this).closest(".summaryData").find(".selectLastRank").val(
						parseInt(selectPosition.next().data("maxrank")) + 10);
			};
		} else {
			$(this).closest(".summaryData").find(".selectLastRank").val(10);
		};
		$(this).closest(".summaryData").find(".lastRank").text(
				$(this).closest(".summaryData").find(".selectLastRank").val());

		radioButton();

		totalVariableRank();
	});

	$(".selectLastRank").on("change", function() {
		$(this).closest(".summaryData").find(".lastRank").text($(this).val());
		let selectLastRank = parseInt($(this).closest(".summaryData").find(".lastRank").text());
		let selectedPosition = $(this).closest(".summaryData").find(".selectLastPositionCode").find("option:selected").data("maxrank");
		$(this).closest(".summaryData").find(".selectLastPositionCode").find("option").each(function(){
			let maxRank = parseInt($(this).data("maxrank"));
			let minRank = 0;
			if ($(this).next().length > 0) {
				minRank = parseInt($(this).next().data("maxrank"));
			};
			if (selectLastRank <= maxRank && selectLastRank > minRank
					&& selectedPosition !== $(this).data("maxrank")) {
				$(this).closest("select").val($(this).val());
			};
		});
		$(this).closest(".summaryData").find(".lastRank").text(
				$(this).closest(".summaryData").find(".selectLastRank").val());

		radioButton();

		totalVariableRank();
	});

	$(".okButton").on("click", function() {
		let lastPosition = $(this).closest(".summaryData").find(".selectLastPositionCode").val();
		$(this).closest(".summaryData").find(".selectAppraisalPositionCode").find("option").each(function(){
			let appraisalPosition = $(this).val()
			if (appraisalPosition === lastPosition) {
				$(this).show();
				$(this).prop("selected", true);
				$(this).prop("disabled", false);
			} else {
				$(this).prop("selected", false);
				$(this).prop("disabled", true);
				$(this).hide();
			};
		});
		let appraisalRank = parseInt($(this).closest(".summaryData").find(".lastRank").text());
		$(this).closest(".summaryData").find(".selectAppraisalRank").val(appraisalRank);
		$(this).closest(".summaryData").find(".appraisalRank").text(appraisalRank);

		inputJudgment();

		totalVariableRank();
	});

	$(".noButton").on("click", function() {
		let nowPosition = $(this).closest(".summaryData").find(".nowPositionCode").val();
		$(this).closest(".summaryData").find(".selectAppraisalPositionCode").find("option").each(function(){
			let appraisalPosition = $(this).val()
			if (appraisalPosition === nowPosition) {
				$(this).show();
				$(this).prop("selected", true);
				$(this).prop("disabled", false);
			} else {
				$(this).prop("selected", false);
				$(this).prop("disabled", true);
				$(this).hide();
			};
		});
		let appraisalRank = parseInt(parseInt($(this).closest(".summaryData").find(".nowRank").text()));
		$(this).closest(".summaryData").find(".selectAppraisalRank").val(appraisalRank);
		$(this).closest(".summaryData").find(".appraisalRank").text(appraisalRank);

		inputJudgment();

		totalVariableRank();
	});

	$("#nowPersonnelPeriod").on("change", function() {
		buttonChenge();

		personnelPeriod();

		radioButton();
	});

	$("#modelButton").on("click", function(){
		$("#selectYearModel").val(selectYear);
		$("#modelButtonForm").submit();
		return false;
	});

	$(".employeeButton").on("click", function(){
		$("#employeeButtonForm").find("#selectYearEmployee").val($(this).closest("tr").data("year"));
		$("#employeeButtonForm").find("#selectEmployee").val($(this).closest("tr").data("employee"));
		$("#employeeButtonForm").submit();
		return false;
	});

	$(".inputArea").on("change", function(){
		inputJudgment();
	});

	$("#save").on("click", function() {
		let isError = false;
		if(!isError) {
			$("#inputSummary").submit();
			alert("完了しました");
		} else {
			alert("OUT");
		};
		return false;
	});

	$("#confirm").on("click", function() {
    	inputJudgment();
    	let isError = false;
    	$(".inputArea").each(function(){
    		if($(this).attr("data-entered") != undefined) {
    			if($(this).data("entered") === 0) {
    				isError = true;
    			};
    		};
    	});
    	if(!isError) {
    		$("#inputSummary").submit();
    		alert("完了しました");
    	} else {
    		alert("未入力の項目があります。");
    	};
        return false;
    });

    $("#inputSummary").submit(function() {
        $("#iframe").unbind().bind('load', function() {

        	// 結果が見たい場合はココで処理
        });
    });

	function buttonChenge() {
		if(selectYear === nowFiscalYear) {
			switch (parseInt($("#nowPersonnelPeriod").val())) {
			case 0:
				$("#save").prop("disabled", true);
				$("#confirm").prop("disabled", true);
				break;
			case 1:
				$("#save").prop("disabled", false);
				$("#confirm").prop("disabled", true);
				break;
			case 2:
				$("#save").prop("disabled", false);
				$("#confirm").prop("disabled", false);
				break;
			case 3:
				$("#save").prop("disabled", false);
				$("#confirm").prop("disabled", false);
				break;
			case 4:
				$("#save").prop("disabled", true);
				$("#confirm").prop("disabled", true);
				break;
			case 5:
				$("#save").prop("disabled", false);
				$("#confirm").prop("disabled", false);
				break;
			case 6:
				$("#save").prop("disabled", false);
				$("#confirm").prop("disabled", false);
				break;
			};
		} else {
			$("#save").prop("disabled", true);
			$("#confirm").prop("disabled", true);
		};
	};

	function departRank(rankLocation, depart) {
		let departRank = 0;
		$(".summaryData").each(function() {
			let rankYear = $(this).data("year");
			let rankDepart = $(this).data("depart");
			let rankHigher = $(this).data("higher");
			if (depart === "all") {
				if (selectYear === rankYear) {
					departRank += parseInt($(this).find(rankLocation).text());
				};
			} else {
				if (selectYear === rankYear && (depart == rankDepart || depart == rankHigher)) {
					departRank += parseInt($(this).find(rankLocation).text());
				};
			};
		});
		return departRank;
	};

	function departRankWithoutRookie(rankLocation, depart) {
		let departRankWithoutRookie = 0;
		$(".summaryData").each(function() {
			let rankYear = $(this).data("year");
			let rankDepart = $(this).data("depart");
			let rankHigher = $(this).data("higher");
			let isRookie = $(this).data("rookie");
			if (depart === "all") {
				if (selectYear === rankYear && isRookie === 0) {
					departRankWithoutRookie += parseInt($(this).find(rankLocation).text());
				};
			} else {
				if (selectYear === rankYear && isRookie === 0
						&& (depart == rankDepart || depart == rankHigher)) {
					departRankWithoutRookie += parseInt($(this).find(rankLocation).text());
				};
			};
		});
		return departRankWithoutRookie;
	};

	function inputJudgment(){
		$(".inputArea").each(function(){
    		if($(this).val() === "" || parseInt($(this).val()) === 0 || !$(this).val().match(/[^\s\t]/)){
    			$(this).css("background-color","lavenderblush");
    			if($(this).attr("data-entered") != undefined) {
    				$(this).data("entered", 0);
    			}
    		} else {
    			$(this).css("background-color","white");
    			if($(this).attr("data-entered") != undefined) {
    				$(this).data("entered", 1);
    			}
    		}
    	});
	}

	function inputHide(int, location) {
		location.find(".output" + int).show();
		location.find(".input" + int).find(".inputArea").removeAttr("data-entered");
		location.find(".input" + int).hide();
	};

	function inputShow(int, location) {
		location.find(".input" + int).show();
		location.find(".input" + int).find(".inputArea").attr("data-entered", 1);
		location.find(".output" + int).hide();
	};

	function personnelPeriod() {
		$(".summaryData").each(function() {
			let periodYear = $(this).data("year");
			if (nowFiscalYear === periodYear) {
				switch (parseInt($("#nowPersonnelPeriod").val())) {
				case 0:
				case 4:
					inputHide(1, $(this));
					inputHide(2, $(this));
					inputHide(3, $(this));
					inputHide(4, $(this));
					inputHide(5, $(this));
					break;
				case 1:
				case 2:
					inputShow(1, $(this));
					inputShow(2, $(this));
					inputHide(3, $(this));
					inputHide(4, $(this));
					inputHide(5, $(this));
					break;
				case 3:
					inputHide(1, $(this));
					inputShow(2, $(this));
					inputShow(3, $(this));
					inputHide(4, $(this));
					inputHide(5, $(this));
					break;
				case 5:
					inputHide(1, $(this));
					inputHide(2, $(this));
					inputHide(3, $(this));
					inputShow(4, $(this));
					inputHide(5, $(this));
					break;
				case 6:
					inputHide(1, $(this));
					inputHide(2, $(this));
					inputHide(3, $(this));
					inputHide(4, $(this));
					inputShow(5, $(this));
					break;
				};
			};
		});
	};

	function radioButton() {
		$(".summaryData").each(function() {
			$(this).find(".selectAppraisalPositionCode").find("option").each(function() {
				if ($(this).prop("disabled")) {
					$(this).hide();
				}
			});
			let nowRank = parseInt($(this).find(".nowRank").text());
			let lastRank = parseInt($(this).find(".lastRank").text());
			if (parseInt($("#nowPersonnelPeriod").val()) === 6) {
				if (nowRank != lastRank) {
					$(this).find(".radioButton").show();
					$(this).find(".selectAppraisalRank").val(parseInt($(this).find(".appraisalRank").text()));
				} else {
					$(this).find(".radioButton").hide();
					$(this).find(".selectAppraisalPositionCode").find("option").each(function() {
						let optionPosition = $(this).val();
						let continuePosition = $(this).closest(".summaryData").find(".selectLastPositionCode").val();
						if (optionPosition === continuePosition) {
							$(this).show();
							$(this).prop("selected", true);
							$(this).prop("disabled", false);
						} else {
							$(this).prop("selected", false);
							$(this).prop("disabled", true);
							$(this).hide();
						};
					});
					let appraisalRank = parseInt($(this).find(".lastRank").text());
					$(this).find(".selectAppraisalRank").val(appraisalRank);
					$(this).find(".appraisalRank").text(appraisalRank);
				};
			};
		});
	};

	function selectedDepartList() {
		$("#depart").find("option").each(function() {
			let departYear = $(this).data("year");
			if (selectYear !== departYear) {
				$(this).not("#all").hide();
			} else {
				$(this).not("#all").show();
			};
		});
	};

	function selectedPositionList(){
		$(".firmPosition").each(function() {
			let positionYear = $(this).data("year");
			if (selectYear !== positionYear) {
				$(this).hide();
			} else {
				$(this).show();
			};
		});
	};

	function selectedSummaryList(){
		$(".summaryData").each(function() {
			let summaryYear = $(this).data("year");
			let summaryDepart = $(this).data("depart");
			let summaryHigher = $(this).data("higher");
			if (selectDepart === "all") {
				if (selectYear !== summaryYear) {
					$(this).hide();
				} else {
					$(this).show();
				};
			} else {
				if (selectYear !== summaryYear || (selectDepart != summaryDepart && selectDepart != summaryHigher)) {
					$(this).hide();
				} else {
					$(this).show();
				};
			};
		});
	};

	function selectedTargetDate(){
		$(".targetData").each(function() {
			let targetYear = $(this).data("year");
			if (selectYear !== targetYear) {
				$(this).hide();
			} else {
				$(this).show();
			};
		});
	};

	function totalRank() {
		$(".targetData").each(function(){
			let targetDataYear = $(this).data("year");
			if (selectYear === targetDataYear) {
				let totalDepartNowRankWithoutRookie = 0;
				let totalDepartNowRank = 0;

				$(this).find(".departNowRank").each(function(){
					let departNowRank = 0;
					departNowRank = departRankWithoutRookie(".nowRank" , rankDepart);
					$(this).text(departNowRank);
					totalDepartNowRankWithoutRookie += departNowRank;
					totalDepartNowRank += departRank(".nowRank" , $(this).data("depart"));
				});

				$(this).find(".totalDepartNowRankWithoutRookie").text(totalDepartNowRankWithoutRookie);
				$(this).find(".totalDepartNowRank").text(totalDepartNowRank);
			};
		});
	};

	function totalVariableRank() {
		$("#totalNowRank").val(departRank(".nowRank", selectDepart));
		$("#totalLastRank").val(departRank(".lastRank", selectDepart));
		$("#totalAppraisalRank").val(departRank(".appraisalRank", selectDepart));

		let rankList = new Array();
		$(".targetData").each(function(){
			let targetDataYear = $(this).data("year");
			if (selectYear === targetDataYear) {
				let totalDepartLastRankWithoutRookie = 0;
				let totalDepartLastRank = 0;
				let totalDepartAppraisalRankWithoutRookie = 0;
				let totalDepartAppraisalRank = 0;

				$(this).find(".departLastRank").each(function(){
					let departLastRank = 0;
					departLastRank = departRankWithoutRookie(".lastRank" , $(this).data("depart"));
					$(this).text(departLastRank);
					totalDepartLastRankWithoutRookie += departLastRank;
					totalDepartLastRank += departRank(".lastRank" , $(this).data("depart"));
				});

				$(this).find(".departAppraisalRank").each(function(){
					let rankDepart = $(this).data("depart");
					let departAppraisalRank = 0;
					departAppraisalRank = departRankWithoutRookie(".appraisalRank" , $(this).data("depart"));
					$(this).text(departAppraisalRank);
					totalDepartAppraisalRankWithoutRookie += departAppraisalRank;
					totalDepartAppraisalRank += departRank(".appraisalRank" , $(this).data("depart"));
					rankList[rankDepart] = departAppraisalRank;
				});

				$(this).find(".totalDepartLastRankWithoutRookie").text(totalDepartLastRankWithoutRookie);
				$(this).find(".totalDepartLastRank").text(totalDepartLastRank);
				$(this).find(".totalDepartAppraisalRankWithoutRookie").text(totalDepartAppraisalRankWithoutRookie);
				$(this).find(".totalDepartAppraisalRank").text(totalDepartAppraisalRank);

				$(this).find(".departRank").each(function(){
					let rankDepart = $(this).data("depart");
					$(this).closest("tr").find(".departrankresult").val(rankList[rankDepart]);
				});

			};
		});
	};
});