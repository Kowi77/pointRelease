package kov.develop.server.repository;

import kov.develop.server.HibernateUtil;
import kov.develop.shared.PointType;
import kov.develop.shared.Point;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;

public class PointRepository implements Serializable {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Session session;

    public PointRepository() {}

    public static void readAllFromXml(String file) {
        List<Point> points = XmlService.getDbFromXml(file);
        Session session = sessionFactory.openSession();
        Transaction transaction= session.beginTransaction();
        for (Point p : points){
            session.replicate(p, ReplicationMode.OVERWRITE);
        }
        transaction.commit();
    }

    public List<Point> getAllPoints() {
        session = sessionFactory.openSession();
        return session.createCriteria(Point.class).list();
    }

    public List<Point> getAllPointsByType(PointType type) {
        this.session = sessionFactory.openSession();
        return session.createCriteria(Point.class).add(Restrictions.like("type", type)).list();
    }

    public List<Point> getAllPointsByTypeAndCountry(PointType type, String country) {
        this.session = sessionFactory.openSession();
        if (type == null)
            return session.createCriteria(Point.class).add(Restrictions.like("country", country)).list();

        return session.createCriteria(Point.class).add(Restrictions.like("type", type))
                .add(Restrictions.like("country", country)).list();
    }

    public List<Point> getAllPointsByTypeAndCountryAndSity(PointType type, String country, String sity) {
        this.session = sessionFactory.openSession();
        if (type == null)
            return session.createCriteria(Point.class).add(Restrictions.like("country", country))
                    .add(Restrictions.like("sity", sity)).list();

        return session.createCriteria(Point.class).add(Restrictions.like("type", type))
                .add(Restrictions.like("country", country))
                .add(Restrictions.like("sity", sity)).list();
    }

    public Point getPoint(int id) {
        this.session = sessionFactory.openSession();
        return  session.get(Point.class, id);
    }
}
