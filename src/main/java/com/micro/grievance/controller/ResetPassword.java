//package com.micro.grievance.controller;
//
//public class ResetPassword {
//
//	@PostMapping("/updatePassword")
//  public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateRequest request) {
//      String userName = request.getUserName();
//      String newPassword = request.getNewPassword();
//
//      // Update password logic
//      LogisticManager logisticManager = logisticManagerService.getLogisticManagerByUserName(userName);
//      if (logisticManager != null) {
//          logisticManager.setPassword(newPassword);
//          logisticManager.setPasswordExpired(false);
//          logisticManagerService.saveLogisticManager(logisticManager);
//          return ResponseEntity.ok("Password updated successfully");
//      }
//
//      VesselOperator vesselOperator = vesselOperatorService.getVesselOperatorByUserName(userName);
//      if (vesselOperator != null) {
//          vesselOperator.setPassword(newPassword);
//          vesselOperator.setPasswordExpired(false);
//          vesselOperatorService.saveVesselOperator(vesselOperator);
//          return ResponseEntity.ok("Password updated successfully");
//      }
//
//      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//  }
//}
