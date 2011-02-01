package server;


import DQSP.server.DQSPServerPrx;
import DQSP.server.DQSPServerPrxHelper;
import Ice.Communicator;
import Ice.Util;

public class ServerProvider {
	
	
	private Communicator ic;
	private DQSPServerPrx __serverPrx;
	private Ice.ObjectPrx obj;

	
	public DQSPServerPrx get__serverPrx() 
	{
		try
		{
			ic = Util.initialize();
			obj = ic.stringToProxy("DQSPServer:tcp -p 10000 -h 192.168.1.1");
			__serverPrx = DQSPServerPrxHelper.uncheckedCast(obj);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return __serverPrx;
	}

}
