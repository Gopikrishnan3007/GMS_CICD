package com.micro.grievance.controller;

import com.micro.grievance.model.Category;
import com.micro.grievance.model.Department;
import com.micro.grievance.model.Employee;
import com.micro.grievance.model.Grievance;
import com.micro.grievance.model.User;
import com.micro.grievance.service.CategoryService;
import com.micro.grievance.service.DepartmentService;
import com.micro.grievance.service.EmployeeService;
import com.micro.grievance.service.GrievanceService;
import com.micro.grievance.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/grievances")
public class GrievanceController {

	@Autowired
	private GrievanceService grievanceService;

	@Autowired
	private UserService userService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private EmployeeService employeeService;


	
//	@PostMapping
//	public ResponseEntity<String> createGrievance(@RequestParam("userId") int userId,
//	        @RequestParam("departmentId") int departmentId, @RequestParam("categoryId") int categoryId,
//	        @RequestParam(value = "assignedEmployeeId", defaultValue = "0") int assignedEmployeeId, // Default value
//	        @RequestParam("branchName") String branchName,
//	        @RequestParam("description") String description, @RequestParam("status") String status,
//	        @RequestParam("closeConfirmation") Boolean closeConfirmation,
//	        @RequestParam("createdAt") String createdAtStr,
//	        @RequestParam("updatedAt") String updatedAtStr,
//	        @RequestParam("attachment") MultipartFile attachment) {
//
//	    try {
//
//	        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
//	        LocalDateTime createdAt = LocalDateTime.parse(createdAtStr, formatter);
//	        LocalDateTime updatedAt = LocalDateTime.parse(updatedAtStr, formatter);
//
//	        // Validate user
//	        User user = userService.getUserById(userId);
//	        if (user == null) {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
//	        }
//
//	        // Validate department
//	        Department department = departmentService.getDepartmentById(departmentId);
//	        if (department == null) {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department Not Found");
//	        }
//
//	        // Validate category
//	        Category category = categoryService.getCategoryById(categoryId);
//	        if (category == null) {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category Not Found");
//	        }
//
//	        // Validate employee if assignedEmployeeId is not zero
//	        Employee employee = null;
//	        if (assignedEmployeeId != 0) {
//	            employee = employeeService.getEmployeeById(assignedEmployeeId);
//	            if (employee == null) {
//	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not Found");
//	            }
//	        }
//
//	        // Handling file attachment
//	        byte[] attachmentBytes = attachment.getBytes();
//
//	        // Create new Grievance object
//	        Grievance grievance = new Grievance(0, user, department, category, branchName, description, attachmentBytes,
//	                status, employee, closeConfirmation, createdAt, updatedAt);
//
//	        // Save grievance
//	        grievanceService.saveGrievance(grievance);
//
//	        return ResponseEntity.ok("Success");
//
//	    } catch (IOException e) {
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//	                .body("Failure: IOException while handling the attachment");
//	    }
//	}

	@PostMapping
	public ResponseEntity<String> createGrievance(
	        @RequestParam("userId") int userId,
	        @RequestParam("departmentId") int departmentId,
	        @RequestParam("categoryId") int categoryId,
	        @RequestParam(value = "assignedEmployeeId", defaultValue = "0") int assignedEmployeeId, // Default value
	        @RequestParam("branchName") String branchName,
	        @RequestParam("description") String description,
	        @RequestParam("status") String status,
	        @RequestParam("closeConfirmation") Boolean closeConfirmation,
	        @RequestParam("createdAt") String createdAtStr,
	        @RequestParam("updatedAt") String updatedAtStr,
	        @RequestParam(value = "attachment", required = false) MultipartFile attachment, // Attachment is optional
	        @RequestParam(value = "resolvedAttachment", required = false) MultipartFile resolvedAttachment) { // New field

	    try {
	        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
	        LocalDateTime createdAt = LocalDateTime.parse(createdAtStr, formatter);
	        LocalDateTime updatedAt = LocalDateTime.parse(updatedAtStr, formatter);

	        // Validate user
	        User user = userService.getUserById(userId);
	        if (user == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
	        }

	        // Validate department
	        Department department = departmentService.getDepartmentById(departmentId);
	        if (department == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department Not Found");
	        }

	        // Validate category
	        Category category = categoryService.getCategoryById(categoryId);
	        if (category == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category Not Found");
	        }

	        // Validate employee if assignedEmployeeId is not zero
	        Employee employee = null;
	        if (assignedEmployeeId != 0) {
	            employee = employeeService.getEmployeeById(assignedEmployeeId);
	            if (employee == null) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not Found");
	            }
	        }

	        // Handling file attachments
	        byte[] attachmentBytes = attachment != null ? attachment.getBytes() : null;
	        byte[] resolvedAttachmentBytes = resolvedAttachment != null ? resolvedAttachment.getBytes() : null;

	        // Create new Grievance object
	        Grievance grievance = new Grievance(0, user, department, category, branchName, description, attachmentBytes,
	                resolvedAttachmentBytes, status, employee, closeConfirmation, createdAt, updatedAt);

	        // Save grievance
	        grievanceService.saveGrievance(grievance);

	        return ResponseEntity.ok("Success");

	    } catch (IOException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Failure: IOException while handling the attachments");
	    }
	}
	
	

	@PatchMapping("/updateStatusAndAttachment/{grievanceId}")
	public ResponseEntity<String> updateStatusAndAttachment(
	        @PathVariable("grievanceId") int grievanceId,
	        @RequestParam("status") String status,
	        @RequestParam("resolvedAttachment") MultipartFile resolvedAttachment) {

	    try {
	        // Fetch the grievance by ID
	        Grievance grievance = grievanceService.getGrievanceById(grievanceId);
	        if (grievance == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grievance not found");
	        }

	        // Update the status
	        grievance.setStatus(status);

	        // Handle the resolved attachment if provided
	        if (resolvedAttachment != null) {
	            grievance.setResolvedAttachment(resolvedAttachment.getBytes());
	        }

	        // Update the `updatedAt` timestamp
	        grievance.setUpdatedAt(LocalDateTime.now());

	        // Save the updated grievance
	        grievanceService.updateGrievance(grievanceId, grievance);

	        return ResponseEntity.ok("Grievance updated successfully");

	    } catch (IOException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failure: IOException while handling the resolved attachment");
	    }
	}


	

	@GetMapping("/find/{grievanceId}")
	public ResponseEntity<Grievance> getGrievanceById(@PathVariable("grievanceId") int grievanceId) {
		Grievance grievance = grievanceService.getGrievanceById

		(grievanceId);
		if (grievance != null) {
			return ResponseEntity.ok(grievance);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping("/findAll")
	public List<Grievance> getAllGrievances() {
		return grievanceService.getAllGrievances();
	}	
	
	

	@GetMapping("/downloadAttachment/{grievanceId}")
	public ResponseEntity<ByteArrayResource> downloadAttachment(@PathVariable("grievanceId") int grievanceId) {
		Grievance grievance = grievanceService.getGrievanceById(grievanceId);
		if (grievance != null && grievance.getAttachment() != null) {
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
					.header(HttpHeaders.CONTENT_DISPOSITION,
							"attachment; filename=\"grievance_" + grievanceId + ".jpg\"")
					.body(new ByteArrayResource(grievance.getAttachment()));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping("/grievanceStatistics")
	public ResponseEntity<Map<String, Integer>> getGrievanceStatistics() {
	    Map<String, Integer> stats = new HashMap<>();
	    stats.put("total", grievanceService.countAllGrievances());
	    stats.put("pending", grievanceService.countByStatus("Pending"));
	    stats.put("closed", grievanceService.countByStatus("Closed"));
	    stats.put("resolved", grievanceService.countByStatus("Resolved"));
	    return ResponseEntity.ok(stats);
	}

	@GetMapping("/grievanceCountByDepartment")
	public ResponseEntity<Map<String, Integer>> getGrievanceCountByDepartment() {
	    return ResponseEntity.ok(grievanceService.countByDepartment());
	}


	@PutMapping(value = "/update/{grievanceId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> updateGrievance(@PathVariable("grievanceId") int grievanceId,
			@RequestParam("description") String description, @RequestParam("status") String status,
			@RequestParam(value = "attachment", required = false) MultipartFile file) throws IOException {

		Grievance grievance = grievanceService.getGrievanceById(grievanceId);
		if (grievance != null) {
			grievance.setDescription(description);
			grievance.setStatus(status);
			grievance.setUpdatedAt(LocalDateTime.now());

			if (file != null) {
				grievance.setAttachment(file.getBytes());
			}

			grievanceService.updateGrievance(grievanceId, grievance);
			return ResponseEntity.ok("Grievance updated successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grievance not found");
		}
	}

	@DeleteMapping("/delete/{grievanceId}")
	public ResponseEntity<String> deleteGrievance(@PathVariable("grievanceId") int grievanceId) {
		try {
			grievanceService.deleteGrievanceById(grievanceId);
			return ResponseEntity.ok("Grievance deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete grievance");
		}
	}

	@PostMapping("/assignEmployee")
	public ResponseEntity<String> assignEmployee(@RequestParam("grievanceId") int grievanceId,
			@RequestParam("employeeId") Employee employee) {

		try {
			Grievance grievance = grievanceService.getGrievanceById(grievanceId);
			if (grievance != null) {
				grievance.setAssignedEmployee(employee);
				grievance.setUpdatedAt(LocalDateTime.now());
				grievanceService.updateGrievance(grievanceId, grievance);
				return ResponseEntity.ok("Employee assigned successfully");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grievance not found");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to assign employee");
		}
	}

	@PostMapping("/closeGrievance")
	public ResponseEntity<String> closeGrievance(@RequestParam("grievanceId") int grievanceId) {
		try {
			Grievance grievance = grievanceService.getGrievanceById(grievanceId);
			if (grievance != null) {
				grievance.setStatus("Closed");
				grievance.setCloseConfirmation(true);
				grievance.setUpdatedAt(LocalDateTime.now());
				grievanceService.updateGrievance(grievanceId, grievance);
				return ResponseEntity.ok("Grievance closed successfully");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grievance not found");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to close grievance");
		}
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Grievance>> getGrievancesByUserId(@PathVariable("userId") int userId) {
		// Check if the user exists
		User user = userService.getUserById(userId);
		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // User not found
		}

		// Fetch grievances based on userId
		List<Grievance> grievances = grievanceService.getGrievancesByUserId(userId);

		if (grievances.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); // No grievances found
		}

		return ResponseEntity.ok(grievances); // Return the list of grievances
	}
	
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<List<Grievance>> getGrievancesByEmployeeId(@PathVariable("employeeId") int employeeId) {
		// Check if the user exists
		Employee employee = employeeService.getEmployeeById(employeeId);
		if (employee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // User not found
		}

		// Fetch grievances based on userId
		List<Grievance> grievances = grievanceService.getGrievancesByEmployeeId(employeeId);

		if (grievances.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); // No grievances found
		}

		return ResponseEntity.ok(grievances); // Return the list of grievances
	}
	
	@GetMapping("/department/{departmentId}")
	public ResponseEntity<List<Grievance>> getGrievancesByDepartmentId(@PathVariable("departmentId") int departmentId) {
		// Check if the user exists
		Department department = departmentService.getDepartmentById(departmentId);
		if (department == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // User not found
		}

		// Fetch grievances based on userId
		List<Grievance> grievances = grievanceService.getGrievancesByDepartmentId(departmentId);

		if (grievances.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); // No grievances found
		}

		return ResponseEntity.ok(grievances); // Return the list of grievances
	}
	
//	@PutMapping("/{id}")
//    public ResponseEntity<Grievance> updateGrievanceStatus(@PathVariable Integer id, @RequestBody String status) {
//        Grievance updatedGrievance = grievanceService.updateGrievanceStatus(id, status);
//        return ResponseEntity.ok(updatedGrievance);
//    }

	@PatchMapping("/{grievanceId}/status")
	public ResponseEntity<Grievance> updateGrievanceStatus(
	        @PathVariable Integer grievanceId, 
	        @RequestBody String status) {
	    
	    Optional<Grievance> grievance = Optional.ofNullable(grievanceService.getGrievanceById(grievanceId));
	    if (grievance.isPresent()) {
	        Grievance updatedGrievance = grievanceService.updateGrievanceStatus(grievanceId, status);
	        return ResponseEntity.ok(updatedGrievance);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	}
	
	 @PatchMapping("/{grievanceId}/assign/{employeeId}")
	    public ResponseEntity<Grievance> assignEmployeeToGrievance(
	            @PathVariable int grievanceId, 
	            @PathVariable int employeeId) {

	        Grievance updatedGrievance = grievanceService.assignEmployeeToGrievance(grievanceId, employeeId);
	        
	        if (updatedGrievance != null) {
	            return ResponseEntity.ok(updatedGrievance);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }
	 
	 @PatchMapping("/{grievanceId}/confirmation")
	    public ResponseEntity<Grievance> updateGrievanceConfirmation(
	            @PathVariable int grievanceId,
	            @RequestBody boolean closeConfirmation) {

	        Grievance updatedGrievance = grievanceService.updateGrievanceConfirmation(grievanceId, closeConfirmation);

	        if (updatedGrievance != null) {
	            return ResponseEntity.ok(updatedGrievance);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }


	 
	 

}
