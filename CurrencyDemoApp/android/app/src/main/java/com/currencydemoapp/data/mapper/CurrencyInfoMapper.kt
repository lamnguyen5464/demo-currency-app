package com.currencydemoapp.data.mapper

import com.currencydemoapp.data.model.CurrencyInfo
import com.currencydemoapp.data.db.entity.CurrencyInfoEntity

fun CurrencyInfo.toEntity(): CurrencyInfoEntity {
    return CurrencyInfoEntity(
        type = this.type,
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        code = this.code
    )
}

fun CurrencyInfoEntity.toModel(): CurrencyInfo {
    return CurrencyInfo(
        type = this.type,
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        code = this.code
    )
}
