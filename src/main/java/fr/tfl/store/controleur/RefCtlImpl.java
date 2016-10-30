package fr.tfl.store.controleur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.tfl.store.model.RefDTO;
import fr.tfl.store.services.IRefService;

@Controller
public class RefCtlImpl extends AbstractStoreCtlImpl {
	
	/** Logger **/
	private static final Logger logger = LoggerFactory
			.getLogger(RefCtlImpl.class);
	
	
	@Autowired
	private IRefService refService;
	
	@RequestMapping(value="/getRef")	
	public @ResponseBody RefDTO getRef(
			@RequestParam(value = "type",defaultValue="0") int type) {
		logger.info("getRef");
		final RefDTO ref = (RefDTO)refService.loadRef(type);       
        return ref;
	}


}
