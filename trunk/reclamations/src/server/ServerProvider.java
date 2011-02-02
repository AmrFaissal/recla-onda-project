package server;


import DQSP.server.DQSPServerPrx;
import DQSP.server.DQSPServerPrxHelper;
import Ice.Application;
import Ice.Communicator;


public class ServerProvider {
	
	
	private Communicator ic;
	private DQSPServerPrx __serverPrx;
	private Ice.ObjectPrx obj;

	
	
	public ServerProvider() 
	{
			ic = Ice.Application.communicator();
			obj = ic.stringToProxy("DQSPServer:tcp -h 192.168.1.1 -p 10001");
			__serverPrx = DQSPServerPrxHelper.uncheckedCast(obj);
	}
	  
	
	public DQSPServerPrx get__serverPrx() 
	{	
		return this.__serverPrx;
	}

}
