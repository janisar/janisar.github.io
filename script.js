
function getBack() {
	window.location.href = "index.html";
}

function check_sources(sources, current) {
	for (var i = 0; i < sources.length; i++) {
		if (sources[i] === current) {
			return false;
		}
	}
	return true;
}

var source_news = false;
var news_start = 0;
var public_source = "";

function show_news(start, end, source) {
	$(".news_header_text").html(source);
	$(".back").html("<span class='glyphicon glyphicon-arrow-left' onclick='getBack()'></span>");
	source_news = true;
	public_source = source;
	$( ".jumbotron" ).html('<div class = "article_source" id = "article_source"><h2 class = "regular_header"><center>' + source + '</center></h2></div>');
	$( ".jumbotron" ).append('<div class = "source_content"></div>');
	show_specific_source_news(start, end, source);
}

function show_specific_source_news(start, end, source) {
	$.getJSON("http://uptsearch.cloudapp.net/solr/rss/select?q=*:*&wt=json&json.wrf=?&indent=true&start=" + start + "&end="+end+"&fq=source:" + source + "&fl=title,date&sort=date%20desc" , function(data) {
		var object = data.response.docs;
		var date = $.format.date(object[0].date, 'dd MMM yyyy');
		if (news_start === 0) {
			$(".source_content").append("<center><h2>" + date + "</h2></center>");
		}
		for (var i = 0; i < object.length; i++) {
			(function(i) {
				var current_date = $.format.date(object[i].date, 'dd MMM yyyy');
				if ((new Date(date).getTime() > new Date(current_date).getTime())) {
					$(".source_content").append("<center><h2>" + current_date + "</h2></center>");	
					date = current_date;
				}
				$(".source_content").append('<div class = "source_title"><p class = "title_content">' + object[i].title + '</p></div><p>');
			})(i);
		}
		news_start += 10;
	});	
}

$(function(){ 
	$(window).scroll(function()
	{
		if (document.body.scrollHeight - $(this).scrollTop()  <= $(this).height() && source_news)
		{	
			show_specific_source_news(news_start, news_start+10, public_source);
		}	
	});
});

/**
* 
**/
$(function() {
	$.getJSON("http://uptsearch.cloudapp.net/solr/rss/select?q=*:*&wt=json&json.wrf=?&indent=true&fl=source&rows=200&sort=date%20desc" , function(data) {
		var object = data.response.docs;
		var sources = [];
		for (var i = 0; i < object.length; i++) {
			(function(i) {
				var current_source = object[i].source;
				if (check_sources(sources, current_source)) {
					sources.push(current_source);
				}
			})(i);
		}
		for (var i = 0; i < sources.length; i++) {
			(function(i) {
				var index = i;
				$.getJSON("http://uptsearch.cloudapp.net/solr/rss/select?q=*:*&wt=json&json.wrf=?&indent=true&fl=title&fq=source:" + sources[i] + "&rows=3&sort=date%20desc", function(data2) {
					var sub_object = data2.response.docs;
					$( ".jumbotron" ).append('<div class = "article_source" id = "article_source" onclick="show_news(0, 10,\'' + sources[i] + '\')"><h2 class = "header"><center>' + sources[i] + '</center></h2></div>');
					$( ".jumbotron").append("<div class = 'article_titles' id = 'three_titles" + index + "'></div>");
					for (var j = 0; j < 3; j++) {
						$("#three_titles" + index).append('<div class = "title"><p class = "title_content">' + sub_object[j].title + '</p></div>');
					}
				});
			})(i);
		}
	});
});