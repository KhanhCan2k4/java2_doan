package config;

public class Config {
	public static final int PER_PAGE = 1;
	public static final int PER_PAGE_MANAGER = 4;
	public static final int OFFSET = 2;
	
	 public static String getPaginationBar(String url, int id, int total, int page, int perPage, int offset) {
		   if (total <= 0) {
			return "";
		   }
		   
		   int totalLinks = Math.ceilDiv(total, perPage);
		    
		   if (totalLinks <= 1) {
			   return "";
		   }
		   
		   String first = "";
		   String pre = "";
		   String next = "";
		   String last = "";
		   
		   int from = page - offset;
		   int to = page + offset;
		   
		   if (from <= 0) {
			   from = 1;
		   }
		   
		   if (to > totalLinks) {
			   to = totalLinks;
		   }
		   
		   String links = "<ul class='pagination'>";
		   
		   if (page > 1) {
			   first = "<li><a href='"+url+"?page=1&id="+id+"'> << </a></li>";
			   pre = "<li><a href='"+url+"?page="+(page -1)+"&id="+id+"'> < </a></li>";
		   }
		   
		   if (page < totalLinks) {
			   last = "<li><a href=' '> >> </a></li>";
			   next = "<li><a href='"+url+"?page="+(page +1)+"&id="+id+"'>></a></li>";
		   }
		   
		   links += first + pre;
		   
		   for (int i = from; i <= to; i++) {
			   links += "<li class='"+(i == page? "active" : "")+"'><a href='"+url+"?page="+i+"&id="+id+"'>"+i+"</a></li>";
		   }
		   
		   links += next + last;
		   
		   links += "</ul>";
		   
		   return links;
	   }
	  
	 public static String getPaginationBar(String url, String key, int total, int page, int perPage, int offset) {
		   if (total <= 0) {
			return "";
		   }
		   
		   int totalLinks = Math.ceilDiv(total, perPage);
		    
		   if (totalLinks <= 1) {
			   return "";
		   }
		   
		   String first = "";
		   String pre = "";
		   String next = "";
		   String last = "";
		   
		   int from = page - offset;
		   int to = page + offset;
		   
		   if (from <= 0) {
			   from = 1;
		   }
		   
		   if (to > totalLinks) {
			   to = totalLinks;
		   }
		   
		   String links = "<ul class='pagination'>";
		   
		   if (page > 1) {
			   first = "<li><a href='"+url+"?page=1&key="+key+"'> << </a></li>";
			   pre = "<li><a href='"+url+"?page="+(page -1)+"&key="+key+"'> < </a></li>";
		   }
		   
		   if (page < totalLinks) {
			   last = "<li><a href='"+url+"?page="+totalLinks+"&key="+key+"'> >> </a></li>";
			   next = "<li><a href='"+url+"?page="+(page +1)+"&key="+key+"'>></a></li>";
		   }
		   
		   links += first + pre;
		   
		   for (int i = from; i <= to; i++) {
			   links += "<li class='"+(i == page? "active" : "")+"'><a href='"+url+"?page="+i+"&key="+key+"'>"+i+"</a></li>";
		   }
		   
		   links += next + last;
		   
		   links += "</ul>";
		   
		   return links;
	   }
	  
}
