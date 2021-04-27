package com.phvob.daoimpl;

import com.phvob.dao.AccessDao;
import com.phvob.pojo.Access;
import com.phvob.pojo.AccessControl;
import com.phvob.util.TDBConnection;
import org.apache.jena.query.*;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @author phvob
 * @email hbuphvob@163.com
 * @create 2021-04-25 19:48
 */

public class AccessDaoImpl implements AccessDao{
    @Override
    public List<Access> queryAllHostAccess() {
        List<Access> accesses = new ArrayList<Access>();
        Dataset dataset = TDBConnection.getConnection();
        dataset.begin(ReadWrite.READ);
        try {
            String spk = "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX xml: <http://www.w3.org/XML/1998/namespace>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX source: <http://www.semanticweb.org/cuibo/ontologies/2021/3/untitled-ontology-9#>\n" +
                    "select  ?access ?subject ?object ?accessControl ?control\n" +
                    "where{\n" +
                    "  \t?access  rdf:type source:Access.\n" +
                    "   ?accessControl rdf:type source:AccessControl.\n" +
                    "  \t?control rdf:type source:Control.\n" +
                    "  \t?subject rdf:type source:Host.\n" +
                    "  \t?object rdf:type source:Host.\n" +
                    "  \n" +
                    "  \t?access source:hasAccessControl ?accessControl.\n" +
                    "  \t?access source:hasSubject ?subject.\n" +
                    "  \t?access source:hasObject ?object.\n" +
                    "  \t?accessControl source:hasControl ?control.\n" +
                    "\n" +
                    "}";
            QueryExecution qExec = QueryExecutionFactory.create(spk, dataset);
            ResultSet rs = qExec.execSelect();
            //ResultSetFormatter.out(rs) ;

            while (rs.hasNext()) {
                QuerySolution qs = rs.next();

                String accstr = qs.get("?access").toString();
                accstr = accstr.substring(accstr.indexOf("#")+1);
                String substr = qs.get("?subject").toString();
                substr = substr.substring(substr.indexOf("#")+1);
                String obstr = qs.get("?object").toString();
                obstr = obstr.substring(obstr.indexOf("#")+1);
                String accConstr = qs.get("?accessControl").toString();
                accConstr = accConstr.substring(accConstr.indexOf("#")+1);
                String constr = qs.get("?control").toString();
                constr = constr.substring(constr.indexOf("#")+1);
                AccessControl accessControl = new AccessControl(accConstr,constr);
                Access access = new Access(accstr,substr,"Host",obstr,"Host",accessControl);
                accesses.add(access);
            }
        } finally {
            dataset.end();
        }
        return accesses;
    }

    @Override
    public List<Access> queryAccessByHost(String host) {
        List<Access> accesses = new ArrayList<Access>();
        Dataset dataset = TDBConnection.getConnection();
        dataset.begin(ReadWrite.READ);
        try {
            String spk = "PREFIX owl: <http://www.w3.org/2002/07/owl#> \n" +
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX xml: <http://www.w3.org/XML/1998/namespace>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX source: <http://www.semanticweb.org/cuibo/ontologies/2021/3/untitled-ontology-9#>\n" +
                    "select  ?access  ?object ?accessControl ?control\n" +
                    "where{\n" +
                    "  \t?access  rdf:type source:Access.\n" +
                    "  \t?accessControl rdf:type source:AccessControl.\n" +
                    "  \t?control rdf:type source:Control.\n" +
                    "  \t?object rdf:type source:Host.\n" +
                    "\n" +
                    "  \t?access source:hasAccessControl ?accessControl.\n" +
                    "  \t?access source:hasSubject source:"+host+".\n" +
                    "  \t?access source:hasObject ?object.\n" +
                    "  \t?accessControl source:hasControl ?control.\n" +
                    "}";
            QueryExecution qExec = QueryExecutionFactory.create(spk, dataset);
            ResultSet rs = qExec.execSelect();
            //ResultSetFormatter.out(rs) ;

            while (rs.hasNext()) {
                QuerySolution qs = rs.next();

                String accstr = qs.get("?access").toString();
                accstr = accstr.substring(accstr.indexOf("#")+1);
                String substr = host;
                String obstr = qs.get("?object").toString();
                obstr = obstr.substring(obstr.indexOf("#")+1);
                String accConstr = qs.get("?accessControl").toString();
                accConstr = accConstr.substring(accConstr.indexOf("#")+1);
                String constr = qs.get("?control").toString();
                constr = constr.substring(constr.indexOf("#")+1);
                AccessControl accessControl = new AccessControl(accConstr,constr);
                Access access = new Access(accstr,substr,"Host",obstr,"Host",accessControl);
                accesses.add(access);
            }
        } finally {
            dataset.end();
        }
        return accesses;
    }
}
