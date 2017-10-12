package gov.ca.cwds.cals.web.rest.rfa;

import static gov.ca.cwds.cals.Constants.API.RFA_1A_CHILD_DESIRED;
import static gov.ca.cwds.cals.Constants.API.PathParams.RFA_1A_APPLICATION_ID;
import static gov.ca.cwds.cals.Constants.API.RFA_1A_FORMS;
import static gov.ca.cwds.cals.Constants.RFA;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.ChildDesiredServiceBackedResource;
import gov.ca.cwds.cals.service.dto.rfa.ChildDesiredDTO;
import gov.ca.cwds.rest.resources.TypedResourceDelegate;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author CWDS CALS API Team.
 */
@Api(tags = {RFA})
@Path(RFA_1A_FORMS + "/{" + RFA_1A_APPLICATION_ID + "}/" + RFA_1A_CHILD_DESIRED)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RFA1aChildDesiredResource {

  private TypedResourceDelegate<Long, ChildDesiredDTO> resourceDelegate;

  @Inject
  public RFA1aChildDesiredResource(
      @ChildDesiredServiceBackedResource
          TypedResourceDelegate<Long, ChildDesiredDTO> resourceDelegate) {
    this.resourceDelegate = resourceDelegate;
  }

  @UnitOfWork(CALSNS)
  @GET
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Returns Desired Child preferences by RFA-1A Form Id", response = ChildDesiredDTO.class)
  public Response getChildDesired(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long formId) {
    return resourceDelegate.get(formId);
  }

  @UnitOfWork(CALSNS)
  @PUT
  @Timed
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(value = "Update Desired Child Preferences in RFA 1A Form", response = ChildDesiredDTO.class)
  public Response updateDesiredChild(
      @PathParam(RFA_1A_APPLICATION_ID)
      @ApiParam(required = true, name = RFA_1A_APPLICATION_ID, value = "The RFA-1A Form Id")
          Long formId,
      @ApiParam(required = true, name = RFA_1A_CHILD_DESIRED, value = "The RFA-1A Child Desired object")
      @Valid
          ChildDesiredDTO childDesired) {
    return resourceDelegate.update(formId, childDesired);
  }
}
