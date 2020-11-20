package com.temenos.hackathon.analytica.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the api_master database table.
 * 
 */
@Entity
@Table(name="api_master")
@NamedQuery(name="ApiMaster.findAll", query="SELECT a FROM ApiMaster a")
public class ApiMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name="api_name")
	private String apiName;

	//bi-directional many-to-one association to ApiDetail
	@OneToMany(mappedBy="apiMasterBean")
	private List<ApiDetails> apiDetails;

	//bi-directional many-to-one association to ResponseDelay
	@OneToMany(mappedBy="apiMasterBean")
	private List<ResponseDelay> responseDelays;

	public ApiMaster() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApiName() {
		return this.apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public List<ApiDetails> getApiDetails() {
		return this.apiDetails;
	}

	public void setApiDetails(List<ApiDetails> apiDetails) {
		this.apiDetails = apiDetails;
	}

	public ApiDetails addApiDetail(ApiDetails apiDetail) {
		getApiDetails().add(apiDetail);
		apiDetail.setApiMasterBean(this);

		return apiDetail;
	}

	public ApiDetails removeApiDetail(ApiDetails apiDetail) {
		getApiDetails().remove(apiDetail);
		apiDetail.setApiMasterBean(null);

		return apiDetail;
	}

	public List<ResponseDelay> getResponseDelays() {
		return this.responseDelays;
	}

	public void setResponseDelays(List<ResponseDelay> responseDelays) {
		this.responseDelays = responseDelays;
	}

	public ResponseDelay addResponseDelay(ResponseDelay responseDelay) {
		getResponseDelays().add(responseDelay);
		responseDelay.setApiMasterBean(this);

		return responseDelay;
	}

	public ResponseDelay removeResponseDelay(ResponseDelay responseDelay) {
		getResponseDelays().remove(responseDelay);
		responseDelay.setApiMasterBean(null);

		return responseDelay;
	}

}