package pe.edu.nh.mvvm;

import java.util.ArrayList;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.*;

public class TreeViewModel {
	
	private DefaultTreeModel<TreeNode<String>> treeModel;
	
	@Init
	public void init() {
		DefaultTreeNode<String> root = new DefaultTreeNode<>("Root", new ArrayList<>());
		
		DefaultTreeNode<String> padre1 = new DefaultTreeNode<String>("Padre 1", new ArrayList<>());
		DefaultTreeNode<String> padre2 = new DefaultTreeNode<String>("Padre 2", new ArrayList<>());
		
		padre1.add(new DefaultTreeNode<>("Hijo 1.1"));
		padre1.add(new DefaultTreeNode<>("Hijo 1.2"));
		
		padre2.add(new DefaultTreeNode<>("Hijo 2.1"));
		padre2.add(new DefaultTreeNode<>("Hijo 2.2"));
		
		root.add(padre1);
		root.add(padre2);
		
		treeModel = new DefaultTreeModel(root);
	}
	
	
	public DefaultTreeModel<TreeNode<String>> getTreeModel(){
		return treeModel;
	}

}
