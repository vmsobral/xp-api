package br.com.xpapi.mobileapi.parser

import br.com.xpapi.mobileapi.dto.Investments
import br.com.xpapi.mobileapi.dto.Item
import br.com.xpapi.mobileapi.dto.Position
import br.com.xpapi.mobileapi.dto.Positions
import br.com.xpapi.util.toDoubleXP
import org.springframework.stereotype.Component

@Component
class MobileApiInvestmentParser {

    fun parseInvestmentPositions(positions: Positions): List<Investments.InvestmentFund> {
        val investmentFunds = mutableListOf<Investments.InvestmentFund>()

        positions.positions.forEach { position ->
            if (position.name != "Proventos") {
                position.items.forEach {item ->
                    val dischargeNetBalance = parseDischargeNetBalance(position, item)
                    val netBalance = parseAmount(position, item)
                    investmentFunds.add(Investments.InvestmentFund(
                        name = item.title + " - " + item.nickname,
                        dischargeNetBalance = dischargeNetBalance,
                        shareBalance = parseShareBalance(position, item),
                        netBalance = parseAmount(position, item),
                        grossBalance = parseGrossBalance(netBalance, dischargeNetBalance)
                    ))
                }
            }
        }

        return investmentFunds
    }

    /**
     * Rendimento
     */
    private fun parseGrossBalance(netBalance: Double, dischargeNetBalance: Double): Double {
        return netBalance - dischargeNetBalance
    }

    /**
     * Quantidade
     */
    private fun parseShareBalance(position: Position, item: Item): Double {
        if (position.name == "Ações") {
            for (additionalDatum in item.additionalData) {
                if (additionalDatum[0] == "Quantidade")
                    return additionalDatum[1].toDoubleXP()
            }
        }
        return 0.0
    }

    /**
     * Valor Aplicado
     */
    private fun parseDischargeNetBalance(position: Position, item: Item): Double {
        if (position.name != "Ações") {
            for (additionalDatum in item.additionalData) {
                if (additionalDatum[0] == "Valor aplicado")
                    return additionalDatum[1].toDoubleXP()
            }
        }
        return item.amount
    }

    /**
     * Saldo Líquido Atual
     */
    private fun parseAmount(position: Position, item: Item): Double {
        if (position.name == "Fundos de Investimentos") {
            for (additionalDatum in item.additionalData) {
                if (additionalDatum[0] == "Valor líquido")
                    return additionalDatum[1].toDoubleXP()
            }
        }
        return item.amount
    }
}
