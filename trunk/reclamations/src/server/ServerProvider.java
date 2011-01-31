package server;


import DQSP.server.DQSPServerPrx;
import DQSP.server.DQSPServerPrxHelper;

public class ServerProvider {
	
	
	private static Ice.Communicator ic = null;
	private static DQSPServerPrx __serverPrx = null;
	private static Ice.ObjectPrx obj = null;
	
	static
	{
		try
		{
			ic = Ice.Util.initialize();
			obj = ic.stringToProxy("DQSPServer:tcp -h 192.168.1.1 -p 10000");
			__serverPrx = DQSPServerPrxHelper.uncheckedCast(obj);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	
	public static DQSPServerPrx get__serverPrx() 
	{
		return __serverPrx;
	}

}
