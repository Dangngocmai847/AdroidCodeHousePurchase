package com.example.housepurchase.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.housepurchase.databinding.ActivityRequestAppointmentBinding

class RequestAppointmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRequestAppointmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Thiết lập sự kiện cho nút Send
        binding.sendButton.setOnClickListener {
            // Lấy dữ liệu từ các EditText
            val name = binding.nameInput.text.toString()
            val phone = binding.phoneInput.text.toString()
            val email = binding.emailInput.text.toString()
            val idCard = binding.idCardInput.text.toString()

            // Kiểm tra nếu tất cả các trường đều đã được nhập
            if (name.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty() && idCard.isNotEmpty()) {
                // Thực hiện gửi yêu cầu, ví dụ là hiện thông báo
                showToast("Request sent successfully!")
            } else {
                // nếu có trường nào còn trống, yêu cầu người dùng nhập đầy đủ
                showToast("Please fill all fields.")
            }
        }
    }

    // Hàm hiển thị Toast
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
