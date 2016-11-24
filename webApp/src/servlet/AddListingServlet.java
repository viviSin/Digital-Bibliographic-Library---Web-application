package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.*;
import service.*;

@WebServlet("/add_listing")
public class AddListingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddListingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User curUser = (User) request.getSession().getAttribute("currentUser");
		
		if (curUser == null){
			response.sendRedirect(request.getContextPath() + "/signin");
			return;
		}
		
		String action = request.getParameter("action");	
		
		request.setAttribute("curUser", curUser);
		
		if (action == null){
			request.getRequestDispatcher("WEB-INF/pages/addListing.jsp").forward(request, response);
			return;
		}

		action.toLowerCase();
		
		if (action.equals("addListingHome")){
			request.getRequestDispatcher("WEB-INF/pages/addListing.jsp").forward(request, response);
			return;
		}
		
		if (action.equals("searchListing")){
			String searchString = request.getParameter("searchString");
			
			List<Item> results = ItemService.getItemSearchResults(searchString);
			List<OnSaleItem> listed = OnSaleItemService.getOnSaleItemByUserId(curUser.getId());
			System.out.println("SEARCH STRING: "+searchString);
			
			if(results.size() > 0){
				Iterator ritr = results.iterator();
				Item item = null;
				while(ritr.hasNext()){
					item = (Item)ritr.next();
					if (ItemService.userListingItem(curUser, item)){
						ritr.remove();
					}
				}
			}
			for (Item item : results){
				System.out.println(item.getTitle());
			}
			
			if (listed.size() > 0){
				Iterator litr = listed.iterator();
				OnSaleItem listing = null;
				while(litr.hasNext()){
					listing = (OnSaleItem)litr.next();
					if (!ItemService.itemMatchesString(listing.getItem(), searchString)){
						litr.remove();
					}
				}
			}
			
			for (OnSaleItem item : listed){
				System.out.println(item.getItem().getTitle());
			}
			
			request.setAttribute("itemList", results);
			request.setAttribute("listingList", listed);
			request.getRequestDispatcher("WEB-INF/pages/addNewListing.jsp").forward(request, response);
			return;
		}
		
		else if (action.equals("newListing")){
			String itemid = request.getParameter("itemid");
			int nQuantity = Integer.parseInt(request.getParameter("newQuantity"));
			float nPrice = Float.parseFloat(request.getParameter("newPrice"));
			
			OnSaleItem nListing = new OnSaleItem(ItemService.getItemsById(itemid), nPrice, nQuantity, curUser);
			OnSaleItemService.save(nListing);
			request.getRequestDispatcher("WEB-INF/pages/addListing.jsp").forward(request, response);
			return;
		}
		
		else if (action.equals("updateQuantity")){
			String listingid = request.getParameter("listingid");
			int nQuantity = Integer.parseInt(request.getParameter("newQuantity"));
			OnSaleItem nListing = OnSaleItemService.getOnSaleItemById(Integer.parseInt(listingid));
			nListing.setQuantity(nQuantity);
			OnSaleItemService.update(nListing);
			response.sendRedirect("/Assign2/seller");
			return;
		}
		
		else if (action.equals("updatePrice")){
			String listingid = request.getParameter("listingid");
			float nPrice = Float.parseFloat(request.getParameter("newPrice"));
			OnSaleItem nListing = OnSaleItemService.getOnSaleItemById(Integer.parseInt(listingid));
			nListing.setPrice(nPrice);
			OnSaleItemService.update(nListing);
			response.sendRedirect("/Assign2/seller");
			return;
		}
		
		else if (action.equals("addNewListing")){
			String nAuthors = request.getParameter("newAuthors");
			String nPubType = request.getParameter("newPubType");
			Integer nPubYear = Integer.parseInt(request.getParameter("newPubYear"));
			String nTitle = request.getParameter("newTitle");
			String nVenues = request.getParameter("newVenues");
			int nQuantity = Integer.parseInt(request.getParameter("newQuantity"));
			float nPrice = Float.parseFloat(request.getParameter("newPrice"));
			
			//add item to item table
			Item nItem = new Item();
			nItem.setPubltype(nPubType);
			nItem.setPublyear(nPubYear);
			nItem.setTitle(nTitle);
			nItem.setVenues(nVenues);
			
			//add author to author table
			String[] authorList = nAuthors.split(", ");
			ArrayList<Author> authorClassList = new ArrayList<Author>();
			for (String authorName : authorList){
				Author curAuthor = null;
				if (!AuthorService.authorExist(authorName)){
					//new author
					curAuthor = new Author();
					curAuthor.setName(authorName);
					AuthorService.save(curAuthor);
					curAuthor = AuthorService.getAuthorByName(authorName);
				}
				else {
					//get author
					curAuthor = AuthorService.getAuthorByName(authorName);
				}

				authorClassList.add(curAuthor);
				//nItem = ItemService.getItemByTitleAndAuthor(nTitle, authorName);
				//WriteService.save(nItem.getId(), AuthorService.getAuthorByName(authorName).getId());
				
			}
			
			nItem.setAuthors(authorClassList);
			ItemService.save(nItem);
			
			//add listing
			OnSaleItem nListing = new OnSaleItem(nItem, nPrice, nQuantity, curUser);
			OnSaleItemService.save(nListing);
			response.sendRedirect("/Assign2/seller");
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
