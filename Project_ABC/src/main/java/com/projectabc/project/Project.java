package com.projectabc.project;

public class Project {
	int projno;			//������Ʈ ��ȣ (�ڵ�����)
	String projname;	//������Ʈ �̸�
	String projcont;	//������Ʈ ����
	String makedate;	//������Ʈ ������
	String managerid;	//������Ʈ �Ŵ��� ID
	
	public int getProjno() {
		return projno;
	}
	public void setProjno(int projno) {
		this.projno = projno;
	}
	public String getProjname() {
		return projname;
	}
	public void setProjname(String projname) {
		this.projname = projname;
	}
	public String getProjcont() {
		return projcont;
	}
	public void setProjcont(String projcont) {
		this.projcont = projcont;
	}
	public String getMakedate() {
		return makedate;
	}
	public void setMakedate(String makedate) {
		this.makedate = makedate;
	}
	public String getManagerid() {
		return managerid;
	}
	public void setManagerid(String managerid) {
		this.managerid = managerid;
	}

}
