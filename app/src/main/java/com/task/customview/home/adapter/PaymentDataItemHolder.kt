package com.task.customview.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.customview.R
import com.task.customview.formatSum
import com.task.customview.home.entity.Payment
import com.task.customview.home.entity.PaymentOperation
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_date_header.*
import kotlinx.android.synthetic.main.item_payment.*
import kotlinx.android.synthetic.main.item_salary_notification.*

sealed class PaymentDataItemHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    class PaymentItemHolder(
        override val containerView: View
    ) : PaymentDataItemHolder(containerView) {

        companion object {
            fun from(parent: ViewGroup): PaymentItemHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_payment, parent, false)
                return PaymentItemHolder(view)
            }
        }

        fun bind(item: Payment) = with(containerView.context) {
            when (item.operation) {
                PaymentOperation.INCOME -> {
                    iv_operation_type.setImageDrawable(getDrawable(R.drawable.ic_income))
                    tv_operation_sum.setTextColor(resources.getColor(R.color.colorHomeGreen))
                    tv_operation_sum.text = getString(R.string.income_sum, formatSum(item.sum))
                }
                PaymentOperation.OUTCOME -> {
                    iv_operation_type.setImageDrawable(getDrawable(R.drawable.ic_outcome))
                    tv_operation_sum.setTextColor(resources.getColor(R.color.colorHomeText))
                    tv_operation_sum.text = getString(R.string.outcome_sum, formatSum(item.sum))
                }
            }
            tv_operation_description.text = item.description
        }

    }

    class DataHeaderHolder(override val containerView: View) :
        PaymentDataItemHolder(containerView) {
        companion object {
            fun from(parent: ViewGroup): DataHeaderHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_date_header, parent, false)
                return DataHeaderHolder(view)
            }
        }

        fun bind(date: String) = tv_date_header.run { text = date }
    }

    class SalaryNotificationHolder(override val containerView: View) :
        PaymentDataItemHolder(containerView) {

        companion object {
            fun from(
                parent: ViewGroup
            ): SalaryNotificationHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_salary_notification, parent, false)
                return SalaryNotificationHolder(view)
            }
        }

        fun bind(sum: Double) {
            tv_salary_sum.text =
                containerView.context.getString(R.string.income_sum, formatSum(sum))
        }
    }
}
