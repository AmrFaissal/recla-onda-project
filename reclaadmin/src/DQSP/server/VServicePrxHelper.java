// **********************************************************************
//
// Copyright (c) 2003-2009 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.3.1

package DQSP.server;

public final class VServicePrxHelper extends Ice.ObjectPrxHelperBase implements VServicePrx
{
    public static VServicePrx
    checkedCast(Ice.ObjectPrx __obj)
    {
        VServicePrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (VServicePrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::DQSP::server::VService"))
                {
                    VServicePrxHelper __h = new VServicePrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static VServicePrx
    checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        VServicePrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (VServicePrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::DQSP::server::VService", __ctx))
                {
                    VServicePrxHelper __h = new VServicePrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static VServicePrx
    checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        VServicePrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::DQSP::server::VService"))
                {
                    VServicePrxHelper __h = new VServicePrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static VServicePrx
    checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        VServicePrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::DQSP::server::VService", __ctx))
                {
                    VServicePrxHelper __h = new VServicePrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static VServicePrx
    uncheckedCast(Ice.ObjectPrx __obj)
    {
        VServicePrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (VServicePrx)__obj;
            }
            catch(ClassCastException ex)
            {
                VServicePrxHelper __h = new VServicePrxHelper();
                __h.__copyFrom(__obj);
                __d = __h;
            }
        }
        return __d;
    }

    public static VServicePrx
    uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        VServicePrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            VServicePrxHelper __h = new VServicePrxHelper();
            __h.__copyFrom(__bb);
            __d = __h;
        }
        return __d;
    }

    protected Ice._ObjectDelM
    __createDelegateM()
    {
        return new _VServiceDelM();
    }

    protected Ice._ObjectDelD
    __createDelegateD()
    {
        return new _VServiceDelD();
    }

    public static void
    __write(IceInternal.BasicStream __os, VServicePrx v)
    {
        __os.writeProxy(v);
    }

    public static VServicePrx
    __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            VServicePrxHelper result = new VServicePrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }
}
