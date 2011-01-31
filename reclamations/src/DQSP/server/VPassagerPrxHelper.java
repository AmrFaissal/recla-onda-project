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

public final class VPassagerPrxHelper extends Ice.ObjectPrxHelperBase implements VPassagerPrx
{
    public static VPassagerPrx
    checkedCast(Ice.ObjectPrx __obj)
    {
        VPassagerPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (VPassagerPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::DQSP::server::VPassager"))
                {
                    VPassagerPrxHelper __h = new VPassagerPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static VPassagerPrx
    checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        VPassagerPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (VPassagerPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                if(__obj.ice_isA("::DQSP::server::VPassager", __ctx))
                {
                    VPassagerPrxHelper __h = new VPassagerPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static VPassagerPrx
    checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        VPassagerPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::DQSP::server::VPassager"))
                {
                    VPassagerPrxHelper __h = new VPassagerPrxHelper();
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

    public static VPassagerPrx
    checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        VPassagerPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA("::DQSP::server::VPassager", __ctx))
                {
                    VPassagerPrxHelper __h = new VPassagerPrxHelper();
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

    public static VPassagerPrx
    uncheckedCast(Ice.ObjectPrx __obj)
    {
        VPassagerPrx __d = null;
        if(__obj != null)
        {
            try
            {
                __d = (VPassagerPrx)__obj;
            }
            catch(ClassCastException ex)
            {
                VPassagerPrxHelper __h = new VPassagerPrxHelper();
                __h.__copyFrom(__obj);
                __d = __h;
            }
        }
        return __d;
    }

    public static VPassagerPrx
    uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        VPassagerPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            VPassagerPrxHelper __h = new VPassagerPrxHelper();
            __h.__copyFrom(__bb);
            __d = __h;
        }
        return __d;
    }

    protected Ice._ObjectDelM
    __createDelegateM()
    {
        return new _VPassagerDelM();
    }

    protected Ice._ObjectDelD
    __createDelegateD()
    {
        return new _VPassagerDelD();
    }

    public static void
    __write(IceInternal.BasicStream __os, VPassagerPrx v)
    {
        __os.writeProxy(v);
    }

    public static VPassagerPrx
    __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            VPassagerPrxHelper result = new VPassagerPrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }
}
