package servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OnSaleItemDao;
import dto.Author;
import dto.OnSaleItem;

/**
 * Servlet implementation class ItemsServlet
 */
@WebServlet("/index")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage;
		System.out.println(request.getParameter("currentPage"));
		try{currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}catch(Exception e){currentPage = 1;}
		
		List<OnSaleItem> list = null;
		String buttonName;
		buttonName = request.getParameter("buttonName");
		
//===homepage========================================		
		if(buttonName == null || buttonName.equals("home")){
			request.getSession().removeAttribute("onSaleList");
			List<OnSaleItem> onSaleList = OnSaleItemDao.getUnPausedOnSaleItem();
			Collections.shuffle(onSaleList);
			ArrayList<OnSaleItem> randomList = new ArrayList<OnSaleItem>();
			int counter = 0;
			for(OnSaleItem oitem : onSaleList){
				if(counter==10){
					break;
				}
				randomList.add(oitem);
				counter++;
			}
			list = randomList;
		}
		else{ 
			//preferences
			HashSet<String> allowTypes = new HashSet<String>();
		   	allowTypes.add(request.getParameter("article"));
			allowTypes.add(request.getParameter("inproceedings"));
			allowTypes.add(request.getParameter("proceedings"));
			allowTypes.add(request.getParameter("book"));
			allowTypes.add(request.getParameter("incollection"));
			allowTypes.add(request.getParameter("phdthesis"));
			allowTypes.add(request.getParameter("mastersthesis"));
			allowTypes.add(request.getParameter("www"));
			allowTypes.remove(null);
			String searchTitle = request.getParameter("searchTitle");
			String searchAuthor = request.getParameter("searchAuthor");
			String searchVenue = request.getParameter("searchVenue");
			String searchYear2 = request.getParameter("searchYear2");
			String searchYear1 = request.getParameter("searchYear1");
			request.setAttribute("searchTitle", searchTitle);
			request.setAttribute("searchVenue", searchVenue);
			request.setAttribute("searchYear2", searchYear2);
			request.setAttribute("searchYear1", searchYear1);
			request.setAttribute("allowTypes", allowTypes);
			request.setAttribute("searchAuthor", searchAuthor);
//===flip page========================================		
			if(buttonName.equals("      Next      ")){//paging change it to 1,2,3.. if time allows
				list = (ArrayList<OnSaleItem>)request.getSession().getAttribute("onSaleList");
				currentPage++;
			}
			else if(buttonName.equals("    Previous   ")){//paging
				list = (ArrayList<OnSaleItem>)request.getSession().getAttribute("onSaleList");
				currentPage--;
			}
//====search result=======================================		
			else{//it is a search
				list = OnSaleItemDao.getUnPausedOnSaleItem();
					
				if(!searchTitle.isEmpty()){
					list = filterTitle(list,searchTitle);
				}
				if(!searchVenue.isEmpty()){
					System.out.println("\n\n\n\n"+searchVenue);
					list = filterVenue(list,searchVenue);
				}
				if(!searchYear1.isEmpty() || !searchYear2.isEmpty()){
					int y1 = 0, y2 = 0;
					try{Integer.parseInt(searchYear1);}
					catch( Exception e){y1 = 0;}
					try{Integer.parseInt(searchYear2);}
					catch( Exception e){y2 = Integer.MAX_VALUE;}
					list = filterYears(list,y1,y2);
				}
				if(!allowTypes.isEmpty()){
					list=filterPubltype(list,allowTypes);
	 			}
				if(!searchAuthor.isEmpty()){
					list=filterAuthor(list,searchAuthor);
	 			}
				
			}
		}
		request.setAttribute("currentPage",currentPage);
		request.getSession().setAttribute("onSaleList",list);
		request.getRequestDispatcher("WEB-INF/pages/indexPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	
	
	
	
	
	private List<OnSaleItem> filterPubltype(List<OnSaleItem>list,HashSet<String> allowedTypes){
		List<OnSaleItem> rsList = new ArrayList<OnSaleItem>();
			for(OnSaleItem oi : list){
				if(allowedTypes.contains(oi.getItem().getPubltype())){
					rsList.add(oi);
				}
			}
		return rsList;
	}
	
	private List<OnSaleItem> filterTitle(List<OnSaleItem>list,String title){
		List<OnSaleItem> rsList = new ArrayList<OnSaleItem>();
			for(OnSaleItem oi : list){
				String oTitle = oi.getItem().getTitle();
				if(oTitle.contains(title)){
					rsList.add(oi);
				}
			}
		return rsList;
	}
	
	private List<OnSaleItem> filterVenue(List<OnSaleItem>list,String v){
		List<OnSaleItem> rsList = new ArrayList<OnSaleItem>();
			for(OnSaleItem oi : list){
				if(oi.getItem().getVenues()==null){continue;}
				
				if(oi.getItem().getVenues().trim().toLowerCase().contains(v.trim().toLowerCase())){
					rsList.add(oi);
				}
			}
		return rsList;
	}
	
	private List<OnSaleItem> filterYears(List<OnSaleItem>list,int lBound, int uBound){
		List<OnSaleItem> rsList = new ArrayList<OnSaleItem>();
		int year;
			for(OnSaleItem oi : list){
				year = oi.getItem().getPublyear();
				if( year < lBound || year > uBound){
					continue;
				}
				rsList.add(oi);
			}
		return rsList;
	}

	private List<OnSaleItem> filterAuthor(List<OnSaleItem>list,String aName){
		List<OnSaleItem> rsList = new ArrayList<OnSaleItem>();
			for(OnSaleItem oi : list){
				for(Author a : oi.getItem().getAuthors()){
					if(a.getName().contains(aName)){
						rsList.add(oi);
						break;
					}
				}
			}
		return rsList;
	}
	
}