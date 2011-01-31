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

public interface _DQSPServerOperations
{
    java.util.List<AnalyseTable> getAllAnalysisTable(Ice.Current __current);

    java.util.List<Reclamation> getAllReclamations(Ice.Current __current);

    java.util.List<AnalyseTable> getReclamationsSortedList(Ice.Current __current);

    java.util.List<VPassager> getMailingList(Ice.Current __current);

    java.util.List<VService> getAllServices(Ice.Current __current);

    void updateAnalyseTable(String theme, String action, Ice.Current __current);

    void updateActionsEntreprises(String theme, boolean validation, Ice.Current __current);

    void addPassager(int idPassager, String sex, String nom, String mail, String adresse, String codePostale, String phone, String typeReclamateur, String numVol, String prov, String dest, String nationalite, Ice.Current __current);

    void addReclamation(int idPassager, String nomAeroport, String terminale, String nomService, String remarque, String descriptif, Ice.Current __current);
}
