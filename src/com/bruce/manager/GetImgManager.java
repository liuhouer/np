
package com.bruce.manager;
import java.util.List;

import com.bruce.model.GetImg;

public interface GetImgManager {
	
	public GetImg findGetImg(String id);

	public List<GetImg> findAll();

	public void addGetImg(GetImg note);

	public boolean delGetImg(String id);

	public boolean updateGetImg(GetImg note);
	
	
}


