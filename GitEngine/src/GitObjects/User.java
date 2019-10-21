package GitObjects;


import Utils.MagitUtils;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class User {
    private String userName;
    private boolean isOnline;
    private String filesPath;
    private List<Repository> activeRepositories;
    private List<NotificationObject> userNotifications;

    User(String userName) {
        this.userName = userName;
        isOnline = true;
        userNotifications = new LinkedList<>();
        activeRepositories = new LinkedList<>();
        filesPath =  MagitUtils.joinPaths("c:\\magit-ex3", userName);
        createFileInServer(filesPath);
    }

    public void createFileInServer(String pathToCreate){
        File serverUserFile = new File(getPath());
        if (!serverUserFile.exists()){
            serverUserFile.mkdir();
        }
    }
    public String getPath() {
        return filesPath;
    }

    private void setPath(String path) {
        filesPath = path;
    }

    public List<Repository> getActiveRepositories() {
        return activeRepositories;
    }

    public List<NotificationObject> getUserNotifications() {
        return userNotifications;
    }

    public String getUserName() {
        return userName;
    }

    public boolean getIsOnline() {
        return isOnline;
    }

    void setActiveRepositories(List<Repository> activeRepositories) {
        this.activeRepositories = activeRepositories;
    }

    void setOnline(boolean online) {
        isOnline = online;
    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean addRepositoy(Repository repo) {
        if(!getActiveRepositories().contains(repo)){
            activeRepositories.add(repo);
            return true;
        }
        return false;
    }

    public void addNotification(NotificationObject notification) {
        userNotifications.add(notification);
    }

    public Repository getUserRepository(String repoName) {
        for (Repository repo : getActiveRepositories()){
            if (repo.getRepoName().equals(repoName)){
                return repo;
            }
        }
        return null;
    }

    public boolean isRepoExist(String repoName){
        return getUserRepository(repoName) != null;
    }
}
