import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import {
  getAccounts,
  rechargeOperateur,
  toggle
} from "../../actions/ActionComptes";
import styles from "../../style/css/header.css";
import TransactionButton from "./TransactionButton";

class Recharge extends Component {
  constructor() {
    super();

    this.state = {
      // a supprimer
      compte: "",
      montant: 0,
      errors: {
        error: " "
      },
      phoneNumber: 0
    };
  }

  componentWillReceiveProps(nextprops) {
    if (nextprops.errors) {
      this.setState({ errors: nextprops.errors });
    }
  }
  componentDidMount() {
    this.props.getAccounts();
  }

  setText = e => {
    this.setState({ [e.target.name]: e.target.value });
  };

  onSubmit = e => {
    e.preventDefault();

    const virement = {};

    Object.assign(virement, this.state);
    console.log(virement);
    this.props.rechargeOperateur(virement, this.props.history);
  };

  render() {
    const { accounts } = this.props.account;
    const { displaystate } = this.props.display;
    const { errors } = this.props;
    console.log("!!!!!!!" + JSON.stringify(errors));
    return (
      <div className="container-fluid">
        <div className="row">
          {displaystate ? (
            <div className="col-2 style bg-dark">
              <TransactionButton />
            </div>
          ) : (
            <div> </div>
          )}
          <div className={displaystate ? "col-10" : "col-12"}>
            <h3>Recharge</h3>
            <hr className="mb-4" />
            <div className="input-group justify-content-center border  ">
              <form onSubmit={this.onSubmit} className="d-flex flex-column">
                <div className="form-group  ">
                  <label className="form-controle">Numero de compte</label>
                  <div />
                  <select
                    defaultValue={"-1"}
                    name="compte"
                    className="form-controle form-control-lg col-12  "
                    onChange={this.setText}
                  >
                    <option value="-1" disabled>
                      choose
                    </option>
                    {accounts.map(account => (
                      <option
                        value={account.compteNumber}
                        key={account.compteNumber}
                      >
                        {account.compteNumber}
                      </option>
                    ))}
                  </select>
                </div>

                <div className="form-group p-2">
                  <label>montant</label>
                  <input
                    type="number"
                    className="form-control form-control-lg"
                    id="inputPassword4"
                    name="montant"
                    value={this.state.montant}
                    onChange={this.setText}
                  />
                  {errors.monatant || (
                    <div className="invalid-feedback ">{errors.monatant}</div>
                  )}
                  <div className="form-group p-2">
                    <label>numero telephone</label>
                    <input
                      type="text"
                      className="form-control form-control-lg"
                      id="inputPassword4"
                      name="phoneNumber"
                      value={this.state.phoneNumber}
                      onChange={this.setText}
                    />
                    <p>{this.state.errors.montant}</p>
                  </div>
                  <div className="form-group p-2">
                    <button type="submit" className="btn btn-dark ">
                      valider
                    </button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Recharge.propTypes = {
  rechargeOperateur: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
  account: PropTypes.object.isRequired,
  getAccounts: PropTypes.func.isRequired,
  toggle: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  errors: state.errors,
  account: state.account,
  display: state.display
});

export default connect(mapStateToProps, {
  rechargeOperateur,
  getAccounts,
  toggle
})(Recharge);
