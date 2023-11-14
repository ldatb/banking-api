package com.ldatb.learn.banking.service

import com.ldatb.learn.banking.repository.AdminRepository
import org.springframework.stereotype.Service

@Service
class AdminService(private val adminRepository: AdminRepository)